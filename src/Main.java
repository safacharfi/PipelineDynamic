import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

public class Main {
    //1-etape :pipeline vide :pipelin de x Prime
    static ArrayList<Prime> pipeline =new ArrayList<Prime>();
    public static void main(String[] args) {

        //2-etape:Etablissement des cannaux
        SynchronousQueue<Integer> I=new SynchronousQueue<Integer>();
        SynchronousQueue<Integer> O=new SynchronousQueue<Integer>();
        //3-etape :rempissage du pipeline par le 1ere elt =>prime1
        Prime p1=new Prime(1,I,O);
        I=O;
        SynchronousQueue<Integer> out=new SynchronousQueue<Integer>();
        //4eme etape:ajout du 1ere prime dans le pipeline
        pipeline.add(1,p1);
        //5eme etape:lancement du 1ere Prime
        pipeline.get(1).start();


    }
}