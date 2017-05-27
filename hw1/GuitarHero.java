import synthesizer.GuitarString;

/**
 * Created by qianzhenli on 5/27/17.
 */
public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        synthesizer.GuitarString [] AllString= new synthesizer.GuitarString[37];

        for (int i=0; i<37; i++) {
            double frequency= 440*Math.pow(2,(i-24.0)/12);
            AllString[i]= new synthesizer.GuitarString(frequency);
            System.out.print(frequency);
        }


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                try {
                    int temp = keyboard.indexOf(key);
                    AllString[temp].pluck();
                } catch (ArrayIndexOutOfBoundsException e) {
                }

            }

        /* compute the superposition of samples */
        double sample=0.0;

        for (int i=0; i<37; i++) {
             sample += AllString[i].sample();
        }


        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            for (int i=0; i<37; i++) {
                AllString[i].tic();
            }

        }
    }
}
