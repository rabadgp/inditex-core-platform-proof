# Introduction

Para un sistema de ecommerce se mantiene una base de datos que almacena información de los productos para de las marcas
existentes. Para la gestión de los precios de estos productos existe se maneja la siguiente información:

- BRAND_ID : representa un identificador único para la marca a la que pertenece el producto :
- START_DATE / END_DATE: Estos campos definen un rango de fechas durante el cual un precio y una tarifa son aplicables
  para un producto
- PRICE_LIST : Aquí encontramos un identificador que corresponde a la tarifa específica que se aplica a un producto en
  un período determinado.
- PRODUCT_ID : Este campo almacena un identificador único para cada producto, permitiendo la identificación individual
  de los artículos en el catálogo.
- PRIORITY : Un valor numérico que se utiliza para desambiguar la aplicación de precios en caso de que dos tarifas
  coincidan en un rango de fechas. Aplica la tarifa con la prioridad más alta.
- PRICE : Indica el precio final de venta del producto en la moneda correspondiente.
- CURR : Código ISO de la moneda en la que se establece el precio.

An example of the data managed by the persistence system and to be used for the proof is shown below:

| BRAND_ID | START_DATE          | END_DATE             | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|---------------------|----------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59  | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00  | 1          | 35455      | 0        | 25.40 | EUR  |
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.500 | 1          | 35455      | 0        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59  | 1          | 35455      | 0        | 38.95 | EUR  |

## Objectives

The main objective is to create an application or service using the Spring Boot framework that provides a REST endpoint
to perform queries on this pricing database. This REST endpoint should:

- Accept as input parameters the query (or application) date, the product identifier and the brand identifier.
- Provide as output the product identifier, the brand identifier, the rate that applies, the date range during which the
  price applies and the final price to be applied.

# Solution

### Software architecture

Hexagonal architecture is the software architecture chosen to implement the solution to the problem, also following the
DDD principles for the domain layer.

Domain layer is composed of the `domain` module. This is where the objects that make up the domain from the
business point of view are located. The `Product` entity has been identified as AggregateRoot, the `Price` entity as
Entity
and the rest of the identifiers as ValueEntities. It is important to note that due to the lack of business rules in the
target use case the result corresponds to that of an anemic domain.

The application layer is composed of the `application` module, which is in charge of defining use cases, verifying
business rules and orchestrating between the application domain and the infrastructure layer through ports and adapters.
For the implementation of the use cases, a command pattern has been implemented, which facilitates the incorporation and
execution of new use cases, maintaining independence between them at all times.

Infrastructure layer is composed of the `data-access` and `rest` modules, which form the entry and exit gates of the
system.

### REST layer

Application exposes an endpoint to retrieve the product prices following the technical constranits exposed. Once the
application is running, this CURL can be used to retrieve prices information:

```bash
    curl --location 'http://localhost:8080/brands/{brandId}/products/{productId}/price?fee={fee}'
```

where:

- `{brandId}` is the numerical brand identifier
- `{productId}` is the numerical product identifier
- `{fee}` is the timestamp in "YYYY-mm-dd'T'HH:mm:ss'Z'" format

Request example with a successful request `(200 OK)` is:

```bash
  curl --location 'http://localhost:8080/brands/1/products/35455/price?fee=2020-06-15T22%3A00%3A00Z'
```

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceId": 4,
  "startDate": "2020-06-15T16:00:00Z",
  "endDate": "2020-12-31T23:59:59Z",
  "amount": 38.95,
  "currency": "EUR"
}
```

API Rest definition can be reviewed in `./rest/src/main/resources/api/price-openapi.yml` from rest module.

### Persistence layer

An H2 persistence system with the following tables has been used for the data model:

```sql
    create table prices (
        amount float(24) not null,
        price_list integer not null,
        priority smallint not null,
        end_date timestamp(6) not null,
        product_id bigint,
        start_date timestamp(6) not null,
        currency varchar(255) not null,
        primary key (price_list)
    )
```

```sql
    create table products (
        brand_id integer not null,
        product_id bigint not null,
        primary key (product_id)
    )
```

```sql
    alter table if exists prices 
       add constraint FKhpva2t51a39twh6gdkxdcllyf 
       foreign key (product_id) references products
```

# Execution

```bash
mvn spring-boot:run -pl boot 
```