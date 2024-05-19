import java.util.concurrent.SynchronousQueue;

public class Prime extends Thread{
    int index;
    SynchronousQueue<Integer> I;
    SynchronousQueue<Integer> O;
    boolean created;
    Prime(int index, SynchronousQueue<Integer> I,SynchronousQueue<Integer> O){

    }
    public void run(){
        //1ere prime
        if(index==1){
            int n=11;
            int pr=2;

            System.out.println(pr);
            for(int i=3;i<n;i++){
                if(i%pr!=0){
                    //si on n'a pas cree le next prime(prime2) on doit le creer
                    if(created=false){
                        //nouveau cannaux
                        I=O;
                        SynchronousQueue<Integer> out=new SynchronousQueue<Integer>();
                        Prime nextPrime=new Prime(index+1,I,out);
                        Main.pipeline.add(index+1,nextPrime);
                        //lancement du nextprime
Main.pipeline.get(index+1).start();                        created=true;
                    }
                }
                try {
                    O.put(i);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //une fois terminee on doit marquer la fin
            try {
              if(created) { O.put(0);}
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }
        else{
            try {
                int pr = I.take();
                int next;
                do {
                    next = I.take();
                    if (next % pr != 0) {
                        //if le next prime non cree
                        if (created = false) {
                            //on doit cree
                            I = O;
                            SynchronousQueue<Integer> out = new SynchronousQueue<Integer>();
                            Prime nextPrime = new Prime(index + 1, I, out);
                            Main.pipeline.add(index + 1, nextPrime);
                            //lancement du nextprime
                            Main.pipeline.get(index + 1).start();
                            created=true;
                        }
                        O.put(next);
                    }
                } while (next == 0);
                O.put(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }}
