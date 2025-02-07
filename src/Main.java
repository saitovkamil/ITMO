import actions.Actions;
import actions.Story;
import characters.Captain;
import characters.Helper;
import characters.Robinzon;
import characters.Sailor;
import errors.CaptiveException;
import things.Equipment;
import things.MainShip;
import things.SmallBoat;

import java.util.Random;

//Теперь капитан мог без помехи приступить к снаряжению двух лодок, заделке дыры в одной из них и назначению команды для них. Он назначил командиром одной шлюпки своего пассажира и дал в его распоряжение четырех человек; сам же он, его помощник и с ним пятеро матросов сели в другую шлюпку.
//Они отбыли так удачно, что подошли к кораблю в полночь. Когда с корабля можно было расслышать их, капитан приказал Робинзону окликнуть экипаж и сказать, что они привели людей и шлюпку, но что им пришлось долго искать, а затем стал рассказывать разные небылицы. Пока он болтал таким образом, шлюпка причалила к борту.
//Капитан с помощником первые вбежали на палубу и сшибли с ног ударами прикладов второго капитанского помощника и корабельного плотника. Поддерживаемые своими матросами, они взяли в плен всех, кто находился на палубе, и на шканцах, а затем стали запирать люки, чтобы задержать внизу остальных. Тем временем подоспела вторая шлюпка, приставшая к носу корабля; ее команда быстро заняла люк, через который был ход в корабельную кухню, и взяла в плен трех человек.
//Очистив от неприятеля палубу, капитан приказал своему помощнику взять трех матросов и взломать дверь каюты, которую занимал новый капитан, выбранный бунтовщиками. Подняв тревогу, тот вскочил и приготовился к вооруженному отпору с двумя матросами и юнгой, так что, когда капитанский помощник со своими людьми высадили дверь каюты, новый капитан и его приверженцы смело выпалили в них. Помощнику раздробило пулей руку, два матроса тоже оказались ранеными, но никто не был убит
public class Main {
    public static void main(String[] args) throws CaptiveException {
        Captain captain = new Captain("Джо");
        Helper helper = new Helper("Томас");
        Robinzon robinzon = new Robinzon("Робинзон");
        Sailor sailor = new Sailor("Моряк");
        Sailor sailor2 = new Sailor("Моряк");
        SmallBoat boat2 = new SmallBoat("Шлюпка 2");
        MainShip mainship = new MainShip();
        Actions actions = new Actions();
        Random random = new Random();
        Equipment equip = new Equipment(random.nextDouble());
        Story story = new Story();

        actions.startStory(captain, robinzon, sailor, helper);
        actions.rand(story, captain, robinzon, helper);
        actions.attack(robinzon);
        actions.sailorCount(mainship, boat2, sailor2, equip, captain, robinzon, helper);
        actions.startRiot(story, helper, captain, robinzon, sailor);
        actions.fire(story, captain, helper);
        actions.endStory(story, captain, robinzon, helper);
    }
}
