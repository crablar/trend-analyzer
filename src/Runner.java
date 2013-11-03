public class Runner{

    public static void main(String[]args) throws Exception{
        args = new String[]{"Quora_SampleData/hospital.csv"};
        Loader.load(args[0], 5);

    }

}