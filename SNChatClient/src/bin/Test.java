//package bin;
//
//
//public class Test {
//    public static void main(String[] args) {
//        Kek.cf = new ChatFrame();
//        Kek.cf.setVisible(true);
//    }
//}
////        mat();
////    }
//
//    static void mat() {
//        String text = new Scanner(System.in).nextLine();
//        Map<String,String> kek = new HashMap();
//        kek.put("хуй", flower());
//        kek.put("пизд", flower());
//        kek.put("сук", flower());
//        kek.put("блят", flower());
//        kek.put("ебат", flower());
//        kek.put("ебал",flower());
//        kek.put("ебанный", flower());
//        kek.put("ёбаный", flower());
//        kek.put("ёбанный", flower());
//        kek.put("ебаный", flower());
//        kek.put("пиздец", flower());
//        kek.put("ахуел", flower());
//        kek.put("ахуеть", flower());
//        kek.put("пидор", flower());
//        kek.put("член", flower());
//        kek.put("ебала", flower());
//        kek.put("елда", flower());
//        kek.put("машонка", flower());
//        kek.put("уебищ", flower());
//        kek.put("уебищьный", flower());
//        kek.put("нахуй", flower());
//        kek.put("епт", flower());
//        kek.put("ёпт", flower());
//        kek.put("говно", flower());
//        kek.put("моча", flower());
//        String text1=text;
//        for (Map.Entry entry : kek.entrySet()) {
//            text = text.toLowerCase().replaceAll(entry.getKey().toString(), entry.getValue().toString());
//        }
//
//        String[] x1=text.split(" ");
//        String[] x2=text1.split(" ");
//        for (int i = 0; i < x1.length; i++) {
//            if (!x1[i].equalsIgnoreCase(x2[i])){
//                x2[i]=x1[i];
//            }
//        }
//        text="";
//        for (String s : x2) {
//            text+=s+" ";
//        }
//
//        System.out.println(text);
//    }
//
//    static String flower() {
//        Random r = new Random();
//        ArrayList<String> list = new ArrayList<>();
//        list.add("Ромашка");
//        list.add("Василёк");
//        list.add("Одуванчик");
//        list.add("Хризантема");
//        list.add("Лютик");
//        list.add("Роза");
//        list.add("Тюльпан");
//        list.add("Гвоздика");
//        list.add("Гладиолус");
//        list.add("Мимоза");
//        list.add("Нарцисс");
//        list.add("Пион");
//        list.add("Орхидея");
//        return list.get(r.nextInt(list.size()));
//    }
//}
