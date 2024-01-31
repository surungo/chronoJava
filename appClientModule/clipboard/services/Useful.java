package clipboard.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Useful {

    public static void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static boolean testBin(String str) {
        try {
            int number = Integer.parseInt(str);

        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    public static String openUrls(List<String> envs, List<String> endpoints, String clip, List<String> urls, String app, Runtime run) {
        String[] servicos = clip.split("\n");
        envs.forEach(t ->
                endpoints.forEach(e -> {
                            for (String servico : servicos) {
                                urls.add(app + " " + t + "/" + servico + e);
                            }
                        }
                )
        );

        urls.forEach(t -> {
                    try {
                        run.exec(t);

                        Thread.sleep(800);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
        );
        return urls.toString().replaceAll(",", "\n");
    }

    public static String getAppBrowser() {
        //String app = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

        return "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
    }
    public static List<String> getEnvs() {
        List<String> envs = new ArrayList();
        envs.add("https://api.pucrs.br");
        envs.add("https://webapesp.pucrs.br");
        envs.add("https://webaphmg.pucrs.br");
        envs.add("https://webaptst.pucrs.br");
        envs.add("https://webapdsv.pucrs.br");
        return envs;
    }
}