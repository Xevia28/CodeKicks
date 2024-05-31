1. Project Overview:

   Create an e-commerce plaform specializing in selling shoes.
   Users can browse through a wide range of shoes.
   They can add items to their cart, proceed to checkout, and make payments securely.
   The plaform should provide features for user registration and order tracking.

2. Functionality of the Application:

User Interface:

    Design a user-friendly interface with just the command line.

User Registration and Authentication:

    Allow users to register accounts or log in using existing credentials. (We store the user credentials in a file using encryption)
    Capture user information such as name, contact details, and payment preferences.

Product Management:

    Admin panel to manage product inventory, including adding new shoes, updating prices, and removing out-of-stock items.

Shopping Cart and Checkout:

    Enable users to add shoes to their shopping cart and adjust quantities as needed.
    Provide a seamless checkout process with multiple payment options such as credit/debit cards, mBOB, etc.
    Calculate shipping costs and taxes dynamically based on the user's location and chosen delivery method.
    Allow users to review their orders, apply discount codes, and confirm their purchases.

Order Management:

    Notify users of order confirmation and provide an order summary.
    Enable users to track the status of their orders, including processing, shipping, and delivery.
    Admin dashboard to manage orders, process payments, and handle returns or refunds.

Customer Support and Feedback:

    Collect feedback from users regarding their shopping experience, product satisfaction, and suggestions for improvement.

3. Design Principles Integration:

Factory Method: Implement factory methods to create different types of shoes based on categories, brands, and styles.

State: Track the status of orders (e.g., placed, shipped, delivered) using the state pattern to update users accordingly.

Strategy: Implement different pricing strategies, discount rules, and shipping methods based on user preferences and business requirements.

Command: Implement commands for various actions users can perform, such as adding items to the cart, processing orders, or updating user information.

Singleton: Implement singleton to use only one instance of a database

Observer: Implement observers to notify users about order status updates, promotions, or changes in product availability.

Members contributions:
Jigme Namgyal - Command and State
Dendup Tshering - Singleton and Factory 
Chador Wangdi - Observer and Strategy