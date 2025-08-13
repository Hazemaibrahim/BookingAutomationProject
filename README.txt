Booking.com Automation Project

Overview:

This is a Selenium + Java + TestNG automation framework for testing the Booking.com hotel search and reservation flow.
It follows the Page Object Model (POM) design pattern and supports Excel DataProvider for test data-driven testing.

Features
Automated hotel search and booking flow on Booking.com

Page Object Model (POM) for maintainable code

Excel-based DataProvider for flexible test data

Explicit waits for stability

Common scrolling and interaction methods in BasePage

Tech Stack
Java 20

Selenium 4

TestNG

Maven (dependency management)

Apache POI (Excel reading)

How to Run
1- Clone the repository
bash
Copy
Edit
git clone https://github.com/YOUR_USERNAME/booking-automation.git
2-Open the project in IntelliJ IDEA
Let Maven download dependencies automatically.

3-Run the test from IntelliJ
Navigate to src/test/java/tests/BookingTest.java

Click the green Run button to execute the test.


Notes:

Test data is stored in an Excel file inside the resources folder.

Ensure Chrome and ChromeDriver are up to date.

Uses explicit waits for better stability.

Scroll handling is generic and located in BasePage for reuse.



