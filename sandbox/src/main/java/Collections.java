import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
//    String[] langs = new String[4]; //объявлен массив из 4 строк, элементы массива - строки
//    langs[0] = "Java";
//    langs[1] = "C#";
//    langs[2] = "Python";
//    langs[3] = "PHP";

    String[] langs = {"Java", "C#", "Python", "PHP"};

//    //перебор массива
//    for (int i = 0; i < langs.length; i++) {
//      System.out.println("I want to learn " + langs[i]);
//    }

    //перебор коллекции, l - указатель на элемент массива
//    for (String l : langs) {
//      System.out.println("I want to learn " + l);
//    }

    //создаем список, при создании размер = 0, при добавлении и удалении элементов размер изменяется
//    List<String> languages = new ArrayList<String>();
//    languages.add("Java");
//    languages.add("C#");
//    languages.add("Python");
//    languages.add("PHP");

    //передать массив строк в список
    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

    //перебор списка, l - указатель на элемент массива
    for (String l : languages) {
      System.out.println("I want to learn " + l);
    }

    for (int i = 0; i < languages.size(); i++) {
      System.out.println("I want to learn " + languages.get(i));
    }

//    //если не указывать тип списка, вызывать в цикле нужно будет тип Object
//    List languages2 = Arrays.asList("Java", "C#", "Python", "PHP");
//
//    //перебор списка объектов произвольного типа, l - указатель на элемент массива
//    for (Object l2 : languages2) {
//      System.out.println("I want to learn " + l2);
//    }


  }

}
