package by.it.kovalyova.jd02_03;

import java.util.concurrent.Semaphore;

class Buyer extends Thread implements IBuyer, IUseBasket {
    Semaphore s;
    Buyer(int number, Semaphore ss) {
        super("Customer №" + number);
        s = ss;
        Dispatcher.addBuyer();
    }

    @Override
    public void run() {
        enterToMarket();
        takeBasket();
        chooseGoods();
        goToQueue();
        putGoodsToBasket();
        goOut();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this + "entered to the market.");
    }

    @Override
    public void takeBasket() {
        int timeout = Util.random(100,200);
        Util.sleep(timeout);
        System.out.println(this + "took a basket");
    }

    @Override
    public void chooseGoods() {
        System.out.println(this + "started choosing goods");
        int timeout = Util.random(500,2000);
        Util.sleep(timeout);
        System.out.println(this + "made a choice");
    }


    @Override
    public void goToQueue() {
        while(QueueBuyer.getSize()>=49) {
            Util.sleep(50);
        }
        QueueBuyer.add(this);
        s.release();
        System.out.println(this+"waits as "+Integer.toString(QueueBuyer.getSize()));
        synchronized (this){
            try {
                this.wait();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void putGoodsToBasket() {
        int timeout = Util.random(100,200);
        Util.sleep(timeout);
        int goodsCount = Util.random(1,4);
        System.out.println(this + "put " + goodsCount+
                " goods in the basket: " + Util.goods(goodsCount));
    }



    @Override
    public void goOut() {
        System.out.println(this + "went out");
    }

    @Override
    public String toString() {
        return getName() + ": ";
    }
}
