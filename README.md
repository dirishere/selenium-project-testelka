# selenium-testelka
Regression tests project for fakestore.testelka.pl (online store) as a part of the course "Selenium in Java" (Testelka).
The purpose of the tests is to confirm that the following requirements are met after each update of the system components.
According to the requirements, the process is verified from the moment when the product is added to the cart until the payment is made.

## Test Requirements:
1. the user has the possibility to add the selected trip to the cart (product page),
2. the user has the possibility to add the selected trip to the cart (categories page),
3. the user has the possibility to add at least 10 trips to the cart (in total and in combination),
4. the user has the possibility to select the number of trips he wants to purchase (product page),
5. the user can change the amount of the selected trip (single item) in the cart view,
6. the user can delete the trip in the cart view (the entire item),
7. the user has the option to make a purchase without creating an account,
8. the user can see a summary that includes the order number, date, amount, payment method, name and number of products purchased.

##Design Pattern
Tests were based on a design pattern called Page Object Model (to reduce code duplication and improves test maintenance).

##Selenium Grid - configuration
For more information visit https://www.selenium.dev/documentation/en/grid/grid_3/setting_up_your_own_grid/
###hub
1. Download selenium-server-standalone (3.141.59) from https://www.selenium.dev/downloads/.
2. Use command:
``java -jar selenium-server-standalone-3.141.59.jar -role hub``
Selenium grid default port: ``4444``.
Nodes should register to: ``http://{node_address}:4444/grid/register``.
Clients should register to: ``http://{client_address}:4444/wd/hub``.
###node
1. Register new node, use command:
``java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://{node_address}:4444/grid/register``
###configuration file (example)
1. You can create configuration file (``resources/hubconfig.json``, ``resources/nodeconfig.json``) and use it during new node registration.
2. To register new hub with configuration file, use command (or starthub.bat):
``java -jar selenium-server-standalone-3.141.59.jar -role hube -hubConfig hubconfig.json``
3. To register new node with configuration file, use command (or startnode.bat):
``java -jar -Dwebdriver.chrome.driver={chrome_driver_path}/chromedriver.exe selenium-server-standalone-3.141.59.jar -role node -nodeConfig nodeconfig.json``
In this command, you have to provide the path to each used webdriver.