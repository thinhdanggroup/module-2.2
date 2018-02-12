## Model
![](../images/singleton.jpg)
## Create a Singleton Class.
`SingleObject.java`

```java
public class SingleObject {

   //create an object of SingleObject
   // static will create object in one times
   private static SingleObject instance = new SingleObject();

   //make the constructor private so that this class cannot be
   //instantiated
   private SingleObject(){}

   //Get the only object available
   public static SingleObject getInstance(){
      return instance;
   }

   public void showMessage(){
      System.out.println("Hello World!");
   }
}
```
## Get the only object from the singleton class.
`SingletonPatternDemo.java`
```java
public class SingletonPatternDemo {
   public static void main(String[] args) {

      //illegal construct
      //Compile Time Error: The constructor SingleObject() is not visible
      //SingleObject object = new SingleObject();

      //Get the only object available
      SingleObject object = SingleObject.getInstance();

      //show the message
      object.showMessage();
   }
}
```

Output:

```sh
Hello World!
```