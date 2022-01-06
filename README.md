# Principles of Object-Oriented Design

 - #### [S]ingle Responsibility Principle (SRP)
	>The SRP requires that a class should have only a single responsibility.
	
	If a class `SalesOrder` keeps information about a sales order, and in addition has a method `saveOrder()` that saves the `SaleOrder` in a database and a method `exportXML()` that exports the `SalesOrder` in XML format, this design will violate the SRP because there will be different types of users of this class and different reasons for making changes to this class. A change made for one type of user, say change the type of database, may require the re-test, recompilation, and re-linking of the class for the other type of users.
	
- #### [O]pen-Closed Principle (OCP)
	>The OCP requires that each software entity should be open for extension, but closed for modification.
	
	Suppose an `OrderValidation` class has a method `validate(Order order)` that is programmed to validate an order based on a set of hard-coded rules. This design violates the OCP because if the rules change, the `OrderValidation` class has to be modified, tested, and compiled.  

	A better design will be to let the `OrderValidation` class contain a collection of `ValidationRule` objects each of which has a `validate(Order order)` method (perhaps defined in a `Validation` interface) to validate an `Order` using a specific rule, and the `validate(Order order)` method of `OrderValidation` class can simply iterate through those `ValidationRule` objects to validate the order. The new design will satisfy the OCP, because if the rules change, we can just create a new `ValidationRule` object and add it to an `OrderValidation` instance at run time (rather than to the class definition itself).

	This is can also be achieved by using subclasses of a base class `AbstractValidationRule` that has an override-able function `validate(Order order)`. Subclasses can implement the method differently without changing the base class functionality.
	
- #### [L]iskov Substitution Principle (LSP)
	>The LSP requires that objects in a program should be replaceable with instances of their subclasses without altering the correctness of that program.
	
	The Liskov Substitution Principle in computer science is sometimes expressed as a counter-example to the duck test: If it looks like a duck and quacks like a duck but it needs batteries, **you probably have the wrong abstraction**.
	
	The users must be able to use objects of subclasses via references to base classes without noticing any difference. When using an object through its base class interface, the object of a subclass must not expect the user to obey preconditions that are stronger than those required by the base class.

	Suppose a `Rectangle` class has two instance variables `height` and `width`, and a method `setSize(int a, int b)`, which set `height` to `a` and `width` to `b`. Suppose `Square` is a subclass of `Rectangle` and it overrides the inherited method by setting both `height` and `width` to `a`. This design will violate LSP. To see this, consider a client uses a reference variable of type `Rectangle` to call the `setSize()` method to assign different values of `a` and `b`, and then immediately verify if the sizes were set correctly or the area is correctly computed. The results will be different if the variable references to a `Rectangle` object than to a `Square` object.  

	It turns out that in OO programming, a `Square` is not a `Rectangle` at all because it behaves differently from a `Rectangle`.
	
- #### [I]nterface Segregation Principle (ISP)
	>The ISP requires that clients should not be forced to depend on interfaces that they do not use.
	
	Suppose a Vehicle interface shown in the figure is designed for clients to use. This violates ISP because clients are forced to depend on methods they do not use: `HighWay` does not use `stopRadio()` or `ejectCD()`, and `ParkingLot` does not need `accelerate()` or `ejectCD()`. A better design is to design smaller interfaces for different types of clients as shown in the following figure
	
- #### [D]ependency Inversion Principle (DIP)
	>The DIP requires that high level modules should not depend on low level modules, both should depend on abstraction. Also, abstraction should not depend on details, details should depend on abstractions.

	Making a class `Button` associate to another class `Lamp` (because a `Lamp` has a `Button`) is a violation of DIP. A better design will be associate an AbstractButton with an AbstractButtonClient, and define Button as a subclass of the AbstractButton and a Lamp a subclass of the AbstractButtonClient.

	Making an `EBookReader` class to use `PDFBook` class is a violation of DIP because it requires to change the `EBookReader` class to read other types of e-books. A better design is to let `EBookReader` use an interface `EBook` and let `PDFBook` and other types of e-book classes implement `EBook`. Now adding or changing e-book classes will not require any change to `EBookReader` class.

# Code smells

- #### Bloaters
	> Bloaters are code, methods and classes that have increased to such gargantuan proportions that they are hard to work with. Usually these smells do not crop up right away, rather they accumulate over time as the program evolves (and especially when nobody makes an effort to eradicate them).
	-   Long Method
	-   Large Class
	-   Primitive Obsession
	-   Long Parameter List
	-   Data Clumps

- #### Object-Orientation Abusers
	> All these smells are incomplete or incorrect application of object-oriented programming principles.
	-   Alternative Classes with Different Interfaces
	-   Refused Bequest
	-   Switch Statements
	-   Temporary Field
	
- #### Change preventers
	> These smells mean that if you need to change something in one place in your code, you have to make many changes in other places too. Program development becomes much more complicated and expensive as a result.
	-   Divergent Change
	-   Parallel Inheritance Hierarchies
	-   Shotgun Surgery
	
- #### Dispensables
	> A dispensable is something pointless and unneeded whose absence would make the code cleaner, more efficient and easier to understand.
	-   Comments
	-   Duplicate Code
	-   Data Class
	-   Dead Code
	-   Lazy Class
	-   Speculative Generality

- #### Couplers
	> All the smells in this group contribute to excessive coupling between classes or show what happens if coupling is replaced by excessive delegation.
	-   Feature Envy
	-   Inappropriate Intimacy
	-   Incomplete Library Class
	-   Message Chains
	-   Middle Man

# Design patterns

- #### Creational patterns
	> These patterns provide various object creation mechanisms, which increase flexibility and reuse of existing code.
	
	- Factory method
		> Imagine that you’re creating a logistics management application. The first version of your app can only handle transportation by trucks, so the bulk of your code lives inside the `Truck` class. 
		After a while, your app becomes pretty popular. Each day you receive dozens of requests from sea transportation companies to incorporate sea logistics into the app.
		Great news, right? But how about the code? At present, most of your code is coupled to the `Truck` class. Adding `Ships` into the app would require making changes to the entire codebase. Moreover, if later you decide to add another type of transportation to the app, you will probably need to make all of these changes again.
		 
	- Abstract factory
		> Imagine that you’re creating a furniture shop simulator. Your code consists of classes that represent:
		A family of related products, say: `Chair` + `Sofa` + `CoffeeTable`.
		Several variants of this family. For example, products `Chair` + `Sofa` + `CoffeeTable` are available in these variants: `Modern`, `Victorian`, `ArtDeco`.
		 You need a way to create individual furniture objects so that they match other objects of the same family. Customers get quite mad when they receive non-matching furniture.
	- Builder
		> Imagine a complex object that requires laborious, step-by-step initialization of many fields and nested objects. Such initialization code is usually buried inside a monstrous constructor with lots of parameters. Or even worse: scattered all over the client code. For example, let’s think about how to create a `House` object. To build a simple house, you need to construct four walls and a floor, install a door, fit a pair of windows, and build a roof. But what if you want a bigger, brighter house, with a backyard and other goodies (like a heating system, plumbing, and electrical wiring)?
		 
	- Prototype
		> Say you have an object, and you want to create an exact copy of it. How would you do it? First, you have to create a new object of the same class. Then you have to go through all the fields of the original object and copy their values over to the new object. 
		Nice! But there’s a catch. Not all objects can be copied that way because some of the object’s fields may be private and not visible from outside of the object itself.
		 There’s one more problem with the direct approach. Since you have to know the object’s class to create a duplicate, your code becomes dependent on that class. If the extra dependency doesn’t scare you, there’s another catch. Sometimes you only know the interface that the object follows, but not its concrete class, when, for example, a parameter in a method accepts any objects that follow some interface.
	- Singleton	
		> The Singleton pattern solves two problems at the same time, violating the _Single Responsibility Principle_:
		**Ensure that a class has just a single instance**. Why would anyone want to control how many instances a class has? The most common reason for this is to control access to some shared resource—for example, a database or a file.
		Here’s how it works: imagine that you created an object, but after a while decided to create a new one. Instead of receiving a fresh object, you’ll get the one you already created.
- #### Structural patterns
	> These patterns explain how to assemble objects and classes into larger structures while keeping these structures flexible and efficient.

	- Adapter
		> Imagine that you’re creating a stock market monitoring app. The app downloads the stock data from multiple sources in XML format and then displays nice-looking charts and diagrams for the user. 
		At some point, you decide to improve the app by integrating a smart 3rd-party analytics library. But there’s a catch: the analytics library only works with data in JSON format.
		You could change the library to work with XML. However, this might break some existing code that relies on the library. And worse, you might not have access to the library’s source code in the first place, making this approach impossible.
		
	- Bridge
		> Say you have a geometric `Shape` class with a pair of subclasses: `Circle` and `Square`. You want to extend this class hierarchy to incorporate colors, so you plan to create `Red` and `Blue` shape subclasses. However, since you already have two subclasses, you’ll need to create four class combinations such as `BlueCircle` and `RedSquare`. 
		Adding new shape types and colors to the hierarchy will grow it exponentially. For example, to add a triangle shape you’d need to introduce two subclasses, one for each color. And after that, adding a new color would require creating three subclasses, one for each shape type. The further we go, the worse it becomes.
		
	- Composite
		> Using the Composite pattern makes sense only when the core model of your app can be represented as a tree. 
		For example, imagine that you have two types of objects: `Products` and `Boxes`. A `Box` can contain several `Products` as well as a number of smaller `Boxes`. These little `Boxes` can also hold some `Products` or even smaller `Boxes`, and so on.
		Say you decide to create an ordering system that uses these classes. Orders could contain simple products without any wrapping, as well as boxes stuffed with products...and other boxes. How would you determine the total price of such an order?
		
	- Decorator
		> Coffe machine 
		Imagine that you’re working on a notification library which lets other programs notify their users about important events. 
		The initial version of the library was based on the `Notifier` class that had only a few fields, a constructor and a single `send` method. The method could accept a message argument from a client and send the message to a list of emails that were passed to the notifier via its constructor. A third-party app which acted as a client was supposed to create and configure the notifier object once, and then use it each time something important happened.
		At some point, you realize that users of the library expect more than just email notifications. Many of them would like to receive an SMS about critical issues. Others would like to be notified on Facebook and, of course, the corporate users would love to get Slack notifications.
		
	- Facede
		> Imagine that you must make your code work with a broad set of objects that belong to a sophisticated library or framework. Ordinarily, you’d need to initialize all of those objects, keep track of dependencies, execute methods in the correct order, and so on.
	As a result, the business logic of your classes would become tightly coupled to the implementation details of 3rd-party classes, making it hard to comprehend and maintain.
		 
	- Flyweight
		> To have some fun after long working hours, you decided to create a simple video game: players would be moving around a map and shooting each other. You chose to implement a realistic particle system and make it a distinctive feature of the game. Vast quantities of bullets, missiles, and shrapnel from explosions should fly all over the map and deliver a thrilling experience to the player. 
		Upon its completion, you pushed the last commit, built the game and sent it to your friend for a test drive. Although the game was running flawlessly on your machine, your friend wasn’t able to play for long. On his computer, the game kept crashing after a few minutes of gameplay. After spending several hours digging through debug logs, you discovered that the game crashed because of an insufficient amount of RAM. It turned out that your friend’s rig was much less powerful than your own computer, and that’s why the problem emerged so quickly on his machine.
		The actual problem was related to your particle system. Each particle, such as a bullet, a missile or a piece of shrapnel was represented by a separate object containing plenty of data. At some point, when the carnage on a player’s screen reached its climax, newly created particles no longer fit into the remaining RAM, so the program crashed.
		 
	- Proxy
		> Why would you want to control access to an object? Here is an example: you have a massive object that consumes a vast amount of system resources. You need it from time to time, but not always.
		You could implement lazy initialization: create this object only when it’s actually needed. All of the object’s clients would need to execute some deferred initialization code. Unfortunately, this would probably cause a lot of code duplication.
		
- #### Behavioral patterns
	> These patterns are concerned with algorithms and the assignment of responsibilities between objects.

	- Change of reponsibility
		> Imagine that you’re working on an online ordering system. You want to restrict access to the system so only authenticated users can create orders. Also, users who have administrative permissions must have full access to all orders.
		After a bit of planning, you realized that these checks must be performed sequentially. The application can attempt to authenticate a user to the system whenever it receives a request that contains the user’s credentials. However, if those credentials aren’t correct and authentication fails, there’s no reason to proceed with any other checks.
		
	- Command
		> Imagine that you’re working on a new text-editor app. Your current task is to create a toolbar with a bunch of buttons for various operations of the editor. You created a very neat `Button` class that can be used for buttons on the toolbar, as well as for generic buttons in various dialogs.
		While all of these buttons look similar, they’re all supposed to do different things. Where would you put the code for the various click handlers of these buttons? The simplest solution is to create tons of subclasses for each place where the button is used. These subclasses would contain the code that would have to be executed on a button click.
		Before long, you realize that this approach is deeply flawed. First, you have an enormous number of subclasses, and that would be okay if you weren’t risking breaking the code in these subclasses each time you modify the base `Button` class. Put simply, your GUI code has become awkwardly dependent on the volatile code of the business logic.
		
	- Iterator
		> Collections are one of the most used data types in programming. Nonetheless, a collection is just a container for a group of objects.
		Most collections store their elements in simple lists. However, some of them are based on stacks, trees, graphs and other complex data structures.
		But no matter how a collection is structured, it must provide some way of accessing its elements so that other code can use these elements. There should be a way to go through each element of the collection without accessing the same elements over and over.
		This may sound like an easy job if you have a collection based on a list. You just loop over all of the elements. But how do you sequentially traverse elements of a complex data structure, such as a tree? For example, one day you might be just fine with depth-first traversal of a tree. Yet the next day you might require breadth-first traversal. And the next week, you might need something else, like random access to the tree elements.
		
	- Mediator
		> Say you have a dialog for creating and editing customer profiles. It consists of various form controls such as text fields, checkboxes, buttons, etc.
		Some of the form elements may interact with others. For instance, selecting the “I have a dog” checkbox may reveal a hidden text field for entering the dog’s name. Another example is the submit button that has to validate values of all fields before saving the data.
		By having this logic implemented directly inside the code of the form elements you make these elements’ classes much harder to reuse in other forms of the app. For example, you won’t be able to use that checkbox class inside another form, because it’s coupled to the dog’s text field. You can use either all the classes involved in rendering the profile form, or none at all.
		
	- Momento
		> Imagine that you’re creating a text editor app. In addition to simple text editing, your editor can format text, insert inline images, etc.
		At some point, you decided to let users undo any operations carried out on the text. This feature has become so common over the years that nowadays people expect every app to have it. For the implementation, you chose to take the direct approach. Before performing any operation, the app records the state of all objects and saves it in some storage. Later, when a user decides to revert an action, the app fetches the latest snapshot from the history and uses it to restore the state of all objects.
		Let’s think about those state snapshots. How exactly would you produce one? You’d probably need to go over all the fields in an object and copy their values into storage. However, this would only work if the object had quite relaxed access restrictions to its contents. Unfortunately, most real objects won’t let others peek inside them that easily, hiding all significant data in private fields.
		Ignore that problem for now and let’s assume that our objects behave like hippies: preferring open relations and keeping their state public. While this approach would solve the immediate problem and let you produce snapshots of objects’ states at will, it still has some serious issues. In the future, you might decide to refactor some of the editor classes, or add or remove some of the fields. Sounds easy, but this would also require changing the classes responsible for copying the state of the affected objects.
		
	- Observer
		> Weather app
		 Imagine that you have two types of objects: a `Customer` and a `Store`. The customer is very interested in a particular brand of product (say, it’s a new model of the iPhone) which should become available in the store very soon.
		The customer could visit the store every day and check product availability. But while the product is still en route, most of these trips would be pointless.
		On the other hand, the store could send tons of emails (which might be considered spam) to all customers each time a new product becomes available. This would save some customers from endless trips to the store. At the same time, it’d upset other customers who aren’t interested in new products.
		It looks like we’ve got a conflict. Either the customer wastes time checking product availability or the store wastes resources notifying the wrong customers.
		
	- State
		> Gumball machine
		The main idea is that, at any given moment, there’s a _finite_ number of _states_ which a program can be in. Within any unique state, the program behaves differently, and the program can be switched from one state to another instantaneously. However, depending on a current state, the program may or may not switch to certain other states. These switching rules, called _transitions_, are also finite and predetermined.
		You can also apply this approach to objects. Imagine that we have a `Document` class. A document can be in one of three states: `Draft`, `Moderation` and `Published`. The `publish` method of the document works a little bit differently in each state:
		In `Draft`, it moves the document to moderation.
		In `Moderation`, it makes the document public, but only if the current user is an administrator.
		In `Published`, it doesn’t do anything at all. 
		
	- Strategy
		> Duck simulator
		One day you decided to create a navigation app for casual travelers. The app was centered around a beautiful map which helped users quickly orient themselves in any city.
		One of the most requested features for the app was automatic route planning. A user should be able to enter an address and see the fastest route to that destination displayed on the map.
		The first version of the app could only build the routes over roads. People who traveled by car were bursting with joy. But apparently, not everybody likes to drive on their vacation. So with the next update, you added an option to build walking routes. Right after that, you added another option to let people use public transport in their routes.
		However, that was only the beginning. Later you planned to add route building for cyclists. And even later, another option for building routes through all of a city’s tourist attractions.
		
	- Template method
		> Imagine that you’re creating a data mining application that analyzes corporate documents. Users feed the app documents in various formats (PDF, DOC, CSV), and it tries to extract meaningful data from these docs in a uniform format.
		The first version of the app could work only with DOC files. In the following version, it was able to support CSV files. A month later, you “taught” it to extract data from PDF files.
		At some point, you noticed that all three classes have a lot of similar code. While the code for dealing with various data formats was entirely different in all classes, the code for data processing and analysis is almost identical. Wouldn’t it be great to get rid of the code duplication, leaving the algorithm structure intact?
		
	- Visitor
		> Imagine that your team develops an app which works with geographic information structured as one colossal graph. Each node of the graph may represent a complex entity such as a city, but also more granular things like industries, sightseeing areas, etc. The nodes are connected with others if there’s a road between the real objects that they represent. Under the hood, each node type is represented by its own class, while each specific node is an object.
		At some point, you got a task to implement exporting the graph into XML format. At first, the job seemed pretty straightforward. You planned to add an export method to each node class and then leverage recursion to go over each node of the graph, executing the export method. The solution was simple and elegant: thanks to polymorphism, you weren’t coupling the code which called the export method to concrete classes of nodes.
		Unfortunately, the system architect refused to allow you to alter existing node classes. He said that the code was already in production and he didn’t want to risk breaking it because of a potential bug in your changes.
		
		
- #### Other patterns
	> Patterns used in Java EE.

	- MVP
		> In MVP, the Presenter contains the UI business logic for the View. All invocations from the View delegate directly to the Presenter. The Presenter is also decoupled directly from the View and talks to it through an interface. This is to allow mocking of the View in a unit test. One common attribute of MVP is that there has to be a lot of two-way dispatching. For example, when someone clicks the "Save" button, the event handler delegates to the Presenter's "OnSave" method. Once the save is completed, the Presenter will then call back the View through its interface so that the View can display that the save has completed.

	- MVC
		> MVC Pattern stands for Model-View-Controller Pattern. This pattern is used to separate application's concerns.

	- Business Delegate
		> Business Delegate Pattern is used to decouple presentation tier and business tier. It is basically use to reduce communication or remote lookup functionality to business tier code in presentation tier code. In business tier we have following entities.

	- Data mapper
		> Object-relational mapping (ORM, O/RM, and O/R mapping tool) in computer science is a programming technique for converting data between incompatible type systems using object-oriented programming languages. This creates, in effect, a “virtual object database” that can be used from within the programming language. There are both free and commercial packages available that perform object-relational mapping, although some programmers opt to construct their own ORM tools.

	- Active record
		> The active record pattern is an approach to accessing data in a database. A database table or view is wrapped into a class. Thus, an object instance is tied to a single row in the table. After creation of an object, a new row is added to the table upon save. Any object loaded gets its information from the database. When an object is updated the corresponding row in the table is also updated. The wrapper class implements accessor methods or properties for each column in the table or view.

	- Front controller
		> The front controller design pattern is used to provide a centralized request handling mechanism so that all requests will be handled by a single handler. This handler can do the authentication/ authorization/ logging or tracking of request and then pass the requests to corresponding handlers. Following are the entities of this type of design pattern.

	- Intercepting filter
		> The intercepting filter design pattern is used when we want to do some pre-processing / post-processing with request or response of the application. Filters are defined and applied on the request before passing the request to actual target application. Filters can do the authentication/ authorization/ logging or tracking of request and then pass the requests to corresponding handlers. Following are the entities of this type of design pattern.

	- Data transfer object
		> The Transfer Object pattern is used when we want to pass data with multiple attributes in one shot from client to server. Transfer object is also known as Value Object. Transfer Object is a simple POJO class having getter/setter methods and is serializable so that it can be transferred over the network. It does not have any behavior. Server Side business class normally fetches data from the database and fills the POJO and send it to the client or pass it by value. For client, transfer object is read-only. Client can create its own transfer object and pass it to server to update values in database in one shot. Following are the entities of this type of design pattern.

	- Data access object
		> Data Access Object Pattern or DAO pattern is used to separate low level data accessing API or operations from high level business services. Following are the participants in Data Access Object Pattern.

	- Service locator
		> The service locator design pattern is used when we want to locate various services using JNDI lookup. Considering high cost of looking up JNDI for a service, Service Locator pattern makes use of caching technique. For the first time a service is required, Service Locator looks up in JNDI and caches the service object. Further lookup or same service via Service Locator is done in its cache which improves the performance of application to great extent. Following are the entities of this type of design pattern. 

	- Composite entity
		> Composite Entity pattern is used in EJB persistence mechanism. A Composite entity is an EJB entity bean which represents a graph of objects. When a composite entity is updated, internally dependent objects beans get updated automatically as being managed by EJB entity bean. Following are the participants in Composite Entity Bean.