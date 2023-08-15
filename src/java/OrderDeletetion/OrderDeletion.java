/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderDeletetion;

/**
 *
 * @author Admin
 */
import java.util.Timer;
import java.util.TimerTask;
import order.orderDAO;
import order.orderDTO;

public class OrderDeletion {

    public static void scheduleOrderDeletion(orderDTO order, int delaySeconds) {
        Timer timer = new Timer();
        orderDAO o = new orderDAO();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                o.deleteOrderDetail(order);
                // Delete the order with the specified ID
                o.deleteOrder(order);
            }
        };

        // Schedule the task to run after the specified delay in milliseconds
        timer.schedule(task, delaySeconds * 1000);
    }

}
