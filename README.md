
## Android图片选择器

### 主要功能

  * 获取系统数据里面的图片，支持单选多选，分批次加载（每次扫描一定的张数，只在需要获取更多图片时才会继续扫描）

### 使用


````
/**
 * 图片选择页面，扫描系统数据库中记录的图片，分批次加载
 */
public class ImageSelectorActivity extends Activity implements View.OnClickListener {
    /**
     * 启动图片选择页面
     * @param requestCode startActivityForResult的请求码
     * @param activity
     * @param pathList 已选择的图片,可传空
     * @param multipleChoice 是否多选
     * @param maxCount 多选时，可选的最大数量
     */
    public static void startActivityForResult(int requestCode, Activity activity, ArrayList<String> pathList, boolean multipleChoice, int maxCount);
}
````