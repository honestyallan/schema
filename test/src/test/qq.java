package test;

import java.io.*;

public class qq {

    public static void main(String[] args)throws Exception {
        Person person = new Person();
        person.setName("Ð¡Ã÷");
        person.setAge(19);
        person.setSex("ÄÐ");
        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("/data/beta/object.txt")));
        oo.writeObject(person);
        oo.close();
        System.out.print("write ok !\n");
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File("/data/beta/object.txt")));
        Person person1 = (Person) oi.readObject();
        System.out.print(person1.getName()+"\n");
        System.out.print("read ok!\n");
    }
    static class Person implements Serializable{
        /**
		 * 
		 */
		private static final long serialVersionUID = 4742131354712749235L;
		public String name;
        public int age;
        public String sex;
        public String friends;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }


        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

//		public String getFriends() {
//			return friends;
//		}
//
//		public void setFriends(String friends) {
//			this.friends = friends;
//		}

    }
}
