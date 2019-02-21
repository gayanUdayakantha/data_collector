/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_tracker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gayan.u
 */
public class Data_Tracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Writer writer = null;

        try {

            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("log_writes.txt"), "utf-8"));

            System.out.println("Available processors (cores): "
                    + Runtime.getRuntime().availableProcessors());

            writer.write("Available_processors (cores):" + Runtime.getRuntime().availableProcessors() + " | ");

            File[] roots = File.listRoots();

            for (File root : roots) {
                System.out.println("File system root: " + root.getAbsolutePath());
                writer.write("File_system_root:" + root.getAbsolutePath() + " | ");

                String disk_total_space = String.valueOf(root.getTotalSpace());
                double total_space = Math.round(Double.parseDouble(disk_total_space) / 1024 / 1024 / 1024);

                String disk_free_space = String.valueOf(root.getFreeSpace());
                double free_space = Math.round(Double.parseDouble(disk_free_space) / 1024 / 1024 / 1024);

                String disk_usable_space = String.valueOf(root.getUsableSpace());
                double usable_space = Math.round(Double.parseDouble(disk_usable_space) / 1024 / 1024 / 1024);

                writer.write("Total_space_(GB):" + total_space + " | ");
                writer.write("Free_space_(GB):" + free_space + " | ");
                writer.write("Usable_space_(GB):" + usable_space + " | ");

                System.out.println("Total space (GB): " + total_space);
                System.out.println("Free space (GB): " + free_space);
                System.out.println("Usable space (GB): " + usable_space);
            }

            InetAddress ip;
            try {
                ip = InetAddress.getLocalHost();

                System.out.println("Current host name : " + ip.getHostName());
                writer.write("Current_host_name :" + ip.getHostName() + " | ");

                System.out.println("Current IP address : " + ip.getHostAddress());
                writer.write("Current_IP_address :" + ip.getHostAddress() + " | ");

                String nameOS = System.getProperty("os.name");
                System.out.println("Operating system Name=>" + nameOS);
                writer.write("Operating_system_Name:" + nameOS + " | ");

                String osType = System.getProperty("os.arch");
                System.out.println("Operating system type =>" + osType);
                writer.write("Operating_system_type:" + osType + " | ");

                String osVersion = System.getProperty("os.version");
                System.out.println("Operating system version =>" + osVersion);
                writer.write("Operating_system_version:" + osVersion + " | ");

                //    System.out.println(System.getenv("PROCESSOR_IDENTIFIER"));
                //   System.out.println(System.getenv("PROCESSOR_ARCHITECTURE"));
                String username = System.getProperty("user.name");
                System.out.println("Logging user Name : " + username);
                writer.write("Logging_user_Name:" + username + " | ");

                System.out.println("=========================================");
                System.out.println("=========================================");
                System.out.println("=========================================");

                Runtime runtime = Runtime.getRuntime();

                NumberFormat format = NumberFormat.getInstance();

                StringBuilder sb = new StringBuilder();
                long maxMemory_2 = runtime.maxMemory();
                long allocatedMemory = runtime.totalMemory();
                long freeMemory = runtime.freeMemory();

                System.out.println("free memory: " + format.format(freeMemory / 1024) + "");
                System.out.println("allocated memory: " + format.format(allocatedMemory / 1024) + "");
                System.out.println("max memory: " + format.format(maxMemory_2 / 1024) + "");
                System.out.println("total free memory: " + format.format((freeMemory + (maxMemory_2 - allocatedMemory)) / 1024) + "");

                writer.write("free_memory:" + format.format(freeMemory / 1024) + " | ");
                writer.write("allocated_memory:" + format.format(allocatedMemory / 1024) + " | ");
                writer.write("max_memory:" + format.format(maxMemory_2 / 1024) + " | ");
                writer.write("total_free_memory:" + format.format((freeMemory + (maxMemory_2 - allocatedMemory)) / 1024) + " | ");

            } catch (UnknownHostException ex) {
                Logger.getLogger(Data_Tracker.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("================ File Writing Start");

            writer.write("Something");
        } catch (IOException ex) {
            // Report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/
            }
        }

        System.out.println("==================== File Writing Done");
    }

}
