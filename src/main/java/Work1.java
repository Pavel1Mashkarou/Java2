//1) Дана строка sql-запроса "select * from students where ".
        //Сформируйте часть WHERE этого запроса, используя StringBuilder или String.
        //Данные для фильтрации приведены ниже в виде json-строки.
        //Если значение null, то параметр не должен попадать в запрос.
        //Параметры для фильтрации: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
public class Work1 {
    static String[] replaceSymbol(String query){
        String newSqlQuery = query.replaceAll("[{},]","").replaceAll("[,:]"," ");
        String[] where = newSqlQuery.split(" ");
        return where;
    }
    static String fillSqlQuery(String[] array, StringBuilder whereStr, String sql){
        whereStr.append(sql);
        for (int i =0;i<array.length;i+=2){
            if (array[i+1].contains("null")) {
                continue;
            }
            else {
                whereStr.append(array[i]).append("=").append(array[i+1]).append(",");
            }
        }
        whereStr.deleteCharAt(whereStr.length()-1);
        String sql1 = whereStr.toString();
        return sql1;
    }


    public static void main(String[] args) {
        String sql = "select * from students where ";
        StringBuilder whereStr = new StringBuilder();
        String sqlQuery = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";

        String sql1 = fillSqlQuery(replaceSymbol(sqlQuery),whereStr,sql);
        System.out.println(sql1);

    }

}
