
package mylibrary;

import java.util.Objects;


public class App {
    private String hello = "Привет из экземпляра класса App";
    public void run(){
        System.out.println(hello);
    }

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

        
    @Override
    public String toString() {
        return "App{" + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final App other = (App) obj;
        if (!Objects.equals(this.hello, other.hello)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.hello);
        return hash;
    }
    
}
