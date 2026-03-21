package listeners;

import java.io.File;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UploadListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        String basePath = ctx.getRealPath("/uploads");

        String[] subFolders = {"products", "banners"};
        for (String folder : subFolders) {
            File dir = new File(basePath, folder);
            if (!dir.exists()) {
                dir.mkdirs();
                ctx.log("UploadListener: Da tao thu muc " + dir.getAbsolutePath());
            }
        }
        ctx.log("UploadListener: Thu muc uploads san sang.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}