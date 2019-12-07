# backend-tech-challenge

Besides providing exceptional transportation services, Cabify also runs a physical store which sells 3 products:

```
Code         | Name                |  Price
-------------------------------------------------
VOUCHER      | Cabify Voucher      |   5.00€
TSHIRT       | Cabify T-Shirt      |  20.00€
MUG          | Cabify Coffee Mug   |   7.50€
```

Various departments have insisted on the following discounts:

 * The marketing department thinks a buy 2 get 1 free promotion will work best (buy two of the same product, get one free), and would like this to only apply to `VOUCHER` items.

 * The CFO insists that the best way to increase sales is with discounts on bulk purchases (buying x or more of a product, the price of that product is reduced), and requests that if you buy 3 or more `TSHIRT` items, the price per unit should be 19.00€.

This set of rules to apply may change quite frequently in the future.

Your task is to implement a checkout system for this store.

The system should have differentiated client and server components that communicate over the network.

The server should expose the following independent operations:

- Create a new checkout basket
- Add a product to a basket
- Get the total amount in a basket
- Remove the basket

The client must connect user input with those operations via the protocol exposed by the server.

We don't have any DBAs at Cabify, so the service shouldn't use any external databases of any kind. ;)

**You will need to implement:**

- A checkout service


Examples:

    Items: VOUCHER, TSHIRT, MUG
    Total: 32.50€

    Items: VOUCHER, TSHIRT, VOUCHER
    Total: 25.00€

    Items: TSHIRT, TSHIRT, TSHIRT, VOUCHER, TSHIRT
    Total: 81.00€

    Items: VOUCHER, TSHIRT, VOUCHER, VOUCHER, MUG, TSHIRT, TSHIRT
    Total: 74.50€

- A testing suite for the implementes functionalities

**The code should:**
- Be written as production-ready code. You will write production code.
- Be easy to grow and easy to add new functionality.
- Have notes attached, explaining the solution and why certain things are included and others are left out.
- It must not contain executable or object files. Just source files, documentation and data files are allowed.
