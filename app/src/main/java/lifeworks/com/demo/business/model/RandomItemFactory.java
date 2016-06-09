package lifeworks.com.demo.business.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Creates {@link RandomItem} objects
 */
public class RandomItemFactory {

    // Lorem Ipsum text cut into an array, so we can pick and choose random words
    private static String[] randomWords = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Compensabatur, inquit, cum summis doloribus laetitia. Ac tamen hic mallet non dolere. Si longus, levis. Etsi ea quidem, quae adhuc dixisti, quamvis ad aetatem recte isto modo dicerentur. An eiusdem modi? Sed quid attinet de rebus tam apertis plura requirere? Non dolere, inquam, istud quam vim habeat postea videro; Duo Reges: constructio interrete. Contineo me ab exemplis. Et nemo nimium beatus est; Sed haec nihil sane ad rem; De hominibus dici non necesse est. </p> Mihi vero, inquit, placet agi subtilius et, ut ipse dixisti, pressius. Unum nescio, quo modo possit, si luxuriosus sit, finitas cupiditates habere. Potius inflammat, ut coercendi magis quam dedocendi esse videantur. Paulum, cum regem Persem captum adduceret, eodem flumine invectio? Erit enim mecum, si tecum erit. Quae est igitur causa istarum angustiarum? An hoc usque quaque, aliter in vita? Neque enim civitas in seditione beata esse potest nec in discordia dominorum domus; Estne, quaeso, inquam, sitienti in bibendo voluptas? Illa argumenta propria videamus, cur omnia sint paria peccata. Cum audissem Antiochum, Brute, ut solebam, cum M. Quam ob rem tandem, inquit, non satisfacit? Id enim volumus, id contendimus, ut officii fructus sit ipsum officium. Si est nihil nisi corpus, summa erunt illa: valitudo, vacuitas doloris, pulchritudo, cetera. Nulla profecto est, quin suam vim retineat a primo ad extremum. Pisone in eo gymnasio, quod Ptolomaeum vocatur, unaque nobiscum Q. Familiares nostros, credo, Sironem dicis et Philodemum, cum optimos viros, tum homines doctissimos. De vacuitate doloris eadem sententia erit. Universa enim illorum ratione cum tota vestra confligendum puto. Quacumque enim ingredimur, in aliqua historia vestigium ponimus. Quid de Platone aut de Democrito loquar? Odium autem et invidiam facile vitabis. Summum a vobis bonum voluptas dicitur. Ut pulsi recurrant? Ea possunt paria non esse. Fortitudinis quaedam praecepta sunt ac paene leges, quae effeminari virum vetant in dolore. Quaesita enim virtus est, non quae relinqueret naturam, sed quae tueretur.".split(" ");
    // Random integer to use for picking above words
    private static Random rand = new Random();

    /**
     * Creates numberOfItems {@link RandomItem} to return as a List
     *
     * @param numberOfItems number of items to return in List
     * @return List of numberOfItems {@link RandomItem} objects
     */
    public static List<RandomItem> createRandomItems(int numberOfItems) {
        List<RandomItem> randomItems = new ArrayList<>(numberOfItems);
        while (randomItems.size() < numberOfItems) {
            randomItems.add(new RandomItem(generateRandomText(3), generateRandomText(100), "http://loremflickr.com/500/300?random=" + rand.nextInt(randomWords.length)));
        }
        return randomItems;
    }

    /**
     * Generates a random semi-readable text by picking length amount of words from a large Lorem Ipsum text
     *
     * @param length amount of words to be returned
     * @return generated text, split into paragraphs every 50 words
     */
    private static String generateRandomText(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        while (--length > 0) {
            stringBuilder.append(randomWords[rand.nextInt(randomWords.length - 1)]);
            stringBuilder.append(" ");
            if (stringBuilder.length() % 50 == 0) {
                stringBuilder.append("\n\n");
            }
        }
        return stringBuilder.toString();
    }
}
