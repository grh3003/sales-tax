# sales-tax

Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical
products that are exempt. Import duty is an additional sales tax
applicable on all imported goods at a rate of 5%, with no exemptions. When I purchase items
I receive a receipt which lists the name of all the items and their price (including tax),
finishing with the total cost of the items,
and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax
rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of
sales tax.
Write an application that prints out the receipt details for these shopping baskets…


### INPUT:
Input 1:
> 1 book at 12.49
> 1 music CD at 14.99
> 1 chocolate bar at 0.85

Input 2:
> 1 imported box of chocolates at 10.00
> 1 imported bottle of perfume at 47.50

Input 3:
> 1 imported bottle of perfume at 27.99
> 1 bottle of perfume at 18.99
> 1 packet of headache pills at 9.75
> 1 box of imported chocolates at 11.25

### OUTPUT
Output 1:
> 1 book: 12.49
> 1 music CD: 16.49
> 1 chocolate bar: 0.85
> Sales Taxes: 1.50
> Total: 29.83

Output 2:
> 1 imported box of chocolates: 10.50
> 1 imported bottle of perfume: 54.65
> Sales Taxes: 7.65
> Total: 65.15

Output 3:
> 1 imported bottle of perfume: 32.19
> 1 bottle of perfume: 20.89
> 1 packet of headache pills: 9.75
> 1 imported box of chocolates: 11.85
> Sales Taxes: 6.70
> Total: 74.68


# Sales Tax Project

This project consists of two modules: sales-tax-core and sales-tax-ui. The sales-tax-core module contains the business logic and model class (Item) for calculating sales tax. The sales-tax-ui module implements the user interface for interacting with the sales tax calculation functionality.

## Project Structure

- sales-tax-core: Contains the core business logic and model class.
- sales-tax-ui: Implements the user interface for the sales tax calculation.

## Dependencies

The sales-tax-ui module depends on the sales-tax-core module for its business logic. The dependency is managed using Gradle.

## Unit Tests

The sales-tax-core module includes unit tests to validate the correctness of the sales tax calculation logic. The test cases are implemented in the GenerateReceiptTest class.

## Build and Execution

To build and run the project:

1. Clone the sales-tax repository.
2. Navigate to the sales-tax-core directory and run the Gradle build command to generate the JAR file.
3. The generated JAR file will be copied to the sales-tax-ui/lib directory automatically.
4. Navigate to the sales-tax-ui directory and run the UI application.(SalesTaxCalculator main class)
   - Alternatively you can also launch UI by lancher file available in **launcher** folder

**Note : Recommended to use Eclipse to build and run the app**
## Usage
 ### Added Items table 
<img width="896" alt="Screenshot 2023-05-28 at 4 57 05 PM" src="https://github.com/grh3003/sales-tax/assets/133965155/3e7c1cc3-09ba-4f94-a16f-0b7a539e27c9">

### Add Items

<img width="243" alt="Screenshot 2023-05-28 at 4 53 52 PM" src="https://github.com/grh3003/sales-tax/assets/133965155/18b9bd7c-3031-49cc-8343-e4192cdfea46">

### Generated Receipt


<img width="351" alt="Screenshot 2023-05-28 at 4 54 09 PM" src="https://github.com/grh3003/sales-tax/assets/133965155/441aab24-680c-4820-b9ab-e1e075dc593a">



