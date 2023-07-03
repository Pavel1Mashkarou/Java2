import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


//2) Дана json-строка (можно сохранить в файл и читать из файла)
       // [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
       // Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
        //Пример вывода:
        //Студент Иванов получил 5 по предмету Математика.
       // Студент Петрова получил 4 по предмету Информатика.
        //Студент Краснов получил 5 по предмету Физика.
public class Work2 {
    static String[] parseJson(String path) throws FileNotFoundException {
        FileReader reader = new FileReader(path);
        StringBuilder builder = new StringBuilder();
        char[] charArray = new char[1024];
        try {
            reader.read(charArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        builder.append(charArray, 0, charArray.length);
        builder.delete(builder.indexOf("]") - 1, builder.length());
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) == '[' || builder.charAt(i) == '}' || builder.charAt(i) == ']') {
                builder.deleteCharAt(i);
            }
            if (builder.charAt(i) == ':') builder.setCharAt(i, ',');
            if (builder.charAt(i) == '{') builder.deleteCharAt(i);
        }
        String result = builder.toString();
        result = result.replaceAll("\"","");
        String[] res = result.split(",");

        return res;
    }


    static void fillString(String[] res){
        for (int i=0; i< res.length;i+=6){
           StringBuilder resStr = new StringBuilder();
           resStr.append("Студент ").append(res[1+i]).append(" получил ").append(res[3+i]).append(" по предмету ") .append(res[5+i]).append('.');
           System.out.println(resStr);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String path = "src/main/resources/file.txt";
        fillString(parseJson(path));


    }

}
