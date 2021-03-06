package market.zy.com.myapplication.network.qiniu.upload;

import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import java.io.File;

/**
 * Created by root on 16-5-4.
 */
public class OverlapUploadService implements IFileUploadService {
    private UploadManager uploadManager;

    private String filePath;

    private byte[] data = new byte[1024];

    private boolean hasBytes = false;

    private File file;

    private String key;

    private String token;

    @Override
    public IFileUploadService putData(String filePath) {
        this.filePath = filePath;
        return this;
    }

    @Override
    public IFileUploadService putData(byte[] data) {
        this.data = data;
        hasBytes = true;
        return this;
    }

    @Override
    public IFileUploadService putData(File file) {
        this.file = file;
        return this;
    }

    @Override
    public IFileUploadService putKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public IFileUploadService getUpFromServer() {
        this.token = null;
        return this;
    }

    @Override
    public void upload(UpCompletionHandler handler, UploadOptions options) {
        uploadManager = Upload.getInstance();

        if (filePath != null) {
            uploadManager.put(filePath, key, token, handler, options);
        }

        if (hasBytes) {
            uploadManager.put(data, key, token, handler, options);
        }

        if (file != null) {
            uploadManager.put(file, key, token, handler, options);
        }
    }
}
