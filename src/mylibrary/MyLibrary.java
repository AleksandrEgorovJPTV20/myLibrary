
package mylibrary;


public class MyLibrary {


    public static void main(String[] args) {
       App app = new App();
       String str = "строка"; 
       System.out.println(app.getClass().toString());
       System.out.println(app.equals(str));
       app.run();
       System.out.println(app.toString());
       app.setHello("Это поле было изменено с помощью метода setHello()");
       app.run();
       System.out.println(app.toString());
    }
    
}
