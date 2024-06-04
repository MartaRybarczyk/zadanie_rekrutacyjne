Short instruction

Application was made using Maven, Spring-boot, and SQLite
Application was divided into Controller files, Service files and Data files

URI to copy and paste into search bar (tested on Google Chrome)
Endpoints:

1. "product/create"
Syntax: localhost:<port>/product/create?<new arg 0>&<new arg 1>&<new arg 2>...
Name is a primary key
Can be saved without given description

2. "product/get_all"
Displays a current collection of all products in table "products"

3. "product/update/{productName}"
Option to edit any data in an already existing product.
Syntax: localhost:<port>/product/update/<already_existing_product_name>?<new arg>&<new arg 2>...

4. "promocode/create"
Syntax: localhost:<port>/promocode/create?<new arg 0>&<new arg 1>&<new arg 2>...
Id is a primary key

5. "promocode/get_all"
Displays a current collection of all promo codes in table "promoCodes"

6. "purchase/create"
Syntax: localhost:<port>/purchase/create?<new arg 0>&<new arg 1>&<new arg 2>...
Id is a primary key

7. "purchase/get_all"
Displays a current collection of all purchases in table "purchases"