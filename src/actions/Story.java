package actions;

import characters.*;
import java.util.Random;
import errors.CaptiveException;

public class Story {
    private Random random = new Random();

    public void characterStats(Captain captain, Robinzon robinzon, Helper helper) {
        if (random.nextDouble() < 0.6) {
            captain.setPower(captain.getPower() + 2);
        } else {
            captain.setPower(captain.getPower() - 1);
        }
        if (random.nextDouble() < 0.6) {
            captain.setIntellect(captain.getIntellect() + 3);
        } else {
            captain.setIntellect(captain.getIntellect() - 1);
        }
        if (random.nextDouble() < 0.6) {
            robinzon.setPower(robinzon.getPower() + 2);
        } else {
            robinzon.setPower(robinzon.getPower() - 1);
        }
        if (random.nextDouble() < 0.6) {
            robinzon.setIntellect(robinzon.getIntellect() + 3);
        } else {
            robinzon.setIntellect(robinzon.getIntellect() - 1);
        }
        if (random.nextDouble() < 0.6) {
            helper.setPower(helper.getPower() + 1);
        } else {
            helper.setPower(helper.getPower() - 1);
        }
        if (random.nextDouble() < 0.6) {
            helper.setIntellect(helper.getIntellect() + 2);
        } else {
            helper.setIntellect(helper.getIntellect() - 1);
        }
    }

    public void riot(Helper helper, Captain captain, Robinzon robinzon, Sailor sailor) throws CaptiveException {
        if (captain.getCaptive() == true) {
            throw new CaptiveException(captain);
        }
        if (sailor.getCaptive() == true) {
            throw new CaptiveException(sailor);
        }
        if (helper.getCaptive() == true) {
            throw new CaptiveException(helper);
        }
        Random random = new Random();
        if (random.nextDouble() < 0.7) {
            System.out.println("Капитан с помощником первые вбежали на палубу и сшибли с ног ударами прикладов второго капитанского помощника и корабельного плотника");
            helper.helpCaptain();
            captain.setPower(captain.getPower() + 3);
            helper.setPower(helper.getPower() + 2);
            helper.setIntellect(helper.getIntellect() + 2);
            captain.setIntellect(captain.getIntellect() + 2);
        } else {
            captain.setCaptive(true);
            helper.setCaptive(true);
            robinzon.setCaptive(true);
            sailor.setCaptive(true);
            System.out.println("Бунтари побеждают в схватке и берут всех в плен.");
        }
    }

    public void fireShip(Captain captain, Helper helper) throws CaptiveException{
        if (captain.getCaptive() == true) {
            throw new CaptiveException(captain);
        }
        if (helper.getCaptive() == true) {
            throw new CaptiveException(helper);
        }
        if (captain.getPower() + helper.getPower() > 12) {
            System.out.println("Поддерживаемые своими матросами, они взяли в плен всех, кто находился на палубе, и на шканцах, а затем стали запирать люки, чтобы задержать внизу остальных.");
            System.out.println("Команда быстро заняла люк, через который был ход в корабельную кухню, и взяла в плен трех человек. Очистив от неприятеля палубу, капитан приказал своему помощнику взять трех матросов и взломать дверь каюты, которую занимал новый капитан, выбранный бунтовщиками.");
        } else {
            captain.setCaptive(true);
            helper.setCaptive(true);
            Random random = new Random();
            if (random.nextDouble() < 0.3) {
                System.out.println("На корабле начинается пожар, про пленных забывают. Капитан и его помощник сгорели в плену");
            } else {
                captain.setCaptive(false);
                helper.setCaptive(false);
                System.out.println("На корабле начался пожар. Пленный капитан и его помощник сбегают.");
            }
        }
    }

    public void end(Captain captain, Robinzon robinzon, Helper helper) throws CaptiveException{
        if (captain.getCaptive() == true) {
            throw new CaptiveException(captain);
        }
        if (helper.getCaptive() == true) {
            throw new CaptiveException(helper);
        }
        if (captain.getIntellect() > 2 && captain.getFear() < 2) {
            robinzon.setPower(robinzon.getPower() + 3);
            System.out.println("Подняв тревогу, тот вскочил и приготовился к вооруженному отпору с двумя матросами и юнгой, так что, когда капитанский помощник со своими людьми высадили дверь каюты, новый капитан и его приверженцы смело выпалили в них. Помощнику раздробило пулей руку, два матроса тоже оказались ранеными, но никто не был убит.");
        } else {
            System.out.println("Капитан, его помощник и юнга начинают перестрелку с бунтарями. У матросов и капитана не хватает сил и их убивают.");
            captain.setCaptive(true);
            robinzon.setCaptive(true);
            helper.setCaptive(true);
        }
    }
}

