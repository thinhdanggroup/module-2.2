## Before dependency injection



```java
public class EmailService {

	public void sendEmail(String message, String receiver){
		//logic to send email
		System.out.println("Email sent to "+receiver+ " with Message="+message);
	}
}

public class MyApplication {

	private EmailService email = new EmailService();
	
	public void processMessages(String msg, String rec){
		//do some msg validation, manipulation logic etc
		this.email.sendEmail(msg, rec);
	}
}

package com.journaldev.java.legacy;

public class MyLegacyTest {

	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.processMessages("Hi Pankaj", "pankaj@abc.com");
	}

}

```

`MyApplication` class is initialize the email service and then use it => hard-coded dependency =>  If we want to switch to some other advanced email service in future, it will require code changes in `MyApplication` class => hard to extend

If we want to provide additional messaging feature such as SMS or Facebook message then we would need to write another application for that.

Testing the application will be very difficult since our application is directly creating the email service instance.


## After dependency injection


### Service component

First, we have Email and SMS services that implement contract for service.

```java
public interface MessageService {

	void sendMessage(String msg, String rec);
}


public class EmailServiceImpl implements MessageService {

	@Override
	public void sendMessage(String msg, String rec) {
		//logic to send email
		System.out.println("Email sent to "+rec+ " with Message="+msg);
	}

} 

public class SMSServiceImpl implements MessageService {

	@Override
	public void sendMessage(String msg, String rec) {
		//logic to send SMS
		System.out.println("SMS sent to "+rec+ " with Message="+msg);
	}

}

```

### Service consumer

declaring contract for consumer classes

```java

public interface Consumer {

	void processMessages(String msg, String rec);
}

public class MyDIApplication implements Consumer{

	private MessageService service;
	
	public MyDIApplication(MessageService svc){
		this.service=svc;
	}
	
	@Override
	public void processMessages(String msg, String rec){
		//do some msg validation, manipulation logic etc
		this.service.sendMessage(msg, rec);
	}

}
```

### Injectors Classes

`MessageServiceInjector` with method declaration that returns the Consumer class.

```java

public interface MessageServiceInjector {

	public Consumer getConsumer();
}

public class EmailServiceInjector implements MessageServiceInjector {

	@Override
	public Consumer getConsumer() {
		return new MyDIApplication(new EmailServiceImpl());
	}

}

public class SMSServiceInjector implements MessageServiceInjector {

	@Override
	public Consumer getConsumer() {
		return new MyDIApplication(new SMSServiceImpl());
	}

}

```

### Program

Service classes are created in injectors. Also if we have to further extend our application to allow facebook messaging, we will have to write `Service` classes and injector classes only.

```java
public class MyMessageDITest {

	public static void main(String[] args) {
		String msg = "Hi Pankaj";
		String email = "pankaj@abc.com";
		String phone = "4088888888";
		MessageServiceInjector injector = null;
		Consumer app = null;
		
		//Send email
		injector = new EmailServiceInjector();
		app = injector.getConsumer();
		app.processMessages(msg, email);
		
		//Send SMS
		injector = new SMSServiceInjector();
		app = injector.getConsumer();
		app.processMessages(msg, phone);
	}

}
```