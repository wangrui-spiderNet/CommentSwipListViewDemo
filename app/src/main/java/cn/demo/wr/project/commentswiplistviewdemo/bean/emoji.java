package cn.demo.wr.project.commentswiplistviewdemo.bean;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import cn.demo.wr.project.commentswiplistviewdemo.R;

/**
 * Created by wangrui on 2016/8/27.
 */

public class emoji {
    //f080-f089为签到表情
    public static int[] mStrs = new int[]{R.drawable.f000, R.drawable.f001,
            R.drawable.f002, R.drawable.f003, R.drawable.f004, R.drawable.f005,
            R.drawable.f006, R.drawable.f007, R.drawable.f008, R.drawable.f009,
            R.drawable.f010, R.drawable.f011, R.drawable.f012, R.drawable.f013,
            R.drawable.f014, R.drawable.f015, R.drawable.f016, R.drawable.f017,
            R.drawable.f018, R.drawable.f019, R.drawable.delete,
            R.drawable.f020, R.drawable.f021, R.drawable.f022, R.drawable.f023,
            R.drawable.f024, R.drawable.f025, R.drawable.f026, R.drawable.f027,
            R.drawable.f028, R.drawable.f029, R.drawable.f030, R.drawable.f031,
            R.drawable.f032, R.drawable.f033, R.drawable.f034, R.drawable.f035,
            R.drawable.f036, R.drawable.f037, R.drawable.f038, R.drawable.f039,
            R.drawable.delete, R.drawable.f040, R.drawable.f041,
            R.drawable.f042, R.drawable.f043, R.drawable.f044, R.drawable.f045,
            R.drawable.f046, R.drawable.f047, R.drawable.f048, R.drawable.f049,
            R.drawable.f050, R.drawable.f051, R.drawable.f052, R.drawable.f053,
            R.drawable.f054, R.drawable.f055, R.drawable.f056, R.drawable.f057,
            R.drawable.f058, R.drawable.f059, R.drawable.delete,
            R.drawable.f060, R.drawable.f061, R.drawable.f062, R.drawable.f063,
            R.drawable.f064, R.drawable.f065, R.drawable.f066, R.drawable.f067,
            R.drawable.f068, R.drawable.f069, R.drawable.f070, R.drawable.f071,
            R.drawable.f072, R.drawable.f073, R.drawable.f074, R.drawable.f075,
            R.drawable.f076, R.drawable.f077, R.drawable.f078, R.drawable.f079,
            R.drawable.f080, R.drawable.f081,
            R.drawable.f082, R.drawable.f083, R.drawable.f084, R.drawable.f085,
            R.drawable.f086, R.drawable.f087, R.drawable.f088, R.drawable.f089,
            R.drawable.delete};

    public static int[] picture = new int[]{R.drawable.f000, R.drawable.f001,
            R.drawable.f002, R.drawable.f003, R.drawable.f004, R.drawable.f005,
            R.drawable.f006, R.drawable.f007, R.drawable.f008, R.drawable.f009,
            R.drawable.f010, R.drawable.f011, R.drawable.f012, R.drawable.f013,
            R.drawable.f014, R.drawable.f015, R.drawable.f016, R.drawable.f017,
            R.drawable.f018, R.drawable.f019, R.drawable.f020, R.drawable.f021,
            R.drawable.f022, R.drawable.f023, R.drawable.f024, R.drawable.f025,
            R.drawable.f026, R.drawable.f027, R.drawable.f028, R.drawable.f029,
            R.drawable.f030, R.drawable.f031, R.drawable.f032, R.drawable.f033,
            R.drawable.f034, R.drawable.f035, R.drawable.f036, R.drawable.f037,
            R.drawable.f038, R.drawable.f039, R.drawable.f040, R.drawable.f041,
            R.drawable.f042, R.drawable.f043, R.drawable.f044, R.drawable.f045,
            R.drawable.f046, R.drawable.f047, R.drawable.f048, R.drawable.f049,
            R.drawable.f050, R.drawable.f051, R.drawable.f052, R.drawable.f053,
            R.drawable.f054, R.drawable.f055, R.drawable.f056, R.drawable.f057,
            R.drawable.f058, R.drawable.f059, R.drawable.f060, R.drawable.f061,
            R.drawable.f062, R.drawable.f063, R.drawable.f064, R.drawable.f065,
            R.drawable.f066, R.drawable.f067, R.drawable.f068, R.drawable.f069,
            R.drawable.f070, R.drawable.f071, R.drawable.f072, R.drawable.f073,
            R.drawable.f074, R.drawable.f075, R.drawable.f076, R.drawable.f077,
            R.drawable.f078, R.drawable.f079, R.drawable.f080, R.drawable.f081,
            R.drawable.f082, R.drawable.f083, R.drawable.f084, R.drawable.f085,
            R.drawable.f086, R.drawable.f087, R.drawable.f088, R.drawable.f089};

    // public static String[] picture = new String[] { "e000.png", "e001.png",
    // "e002.png", "e003.png", "e004.png", "e005.png", "e006.png",
    // "e007.png", "e008.png", "e009.png", "e010.png", "e011.png",
    // "e012.png", "e013.png", "e014.png", "e015.png", "e016.png",
    // "e017.png", "e018.png", "e019.png", "e020.png", "e021.png",
    // "e022.png", "e023.png", "e024.png", "e025.png", "e026.png",
    // "e027.png", "e028.png", "e029.png", "e030.png", "e031.png",
    // "e032.png", "e033.png", "e034.png", "e035.png", "e036.png",
    // "e037.png", "e038.png", "e039.png", "e040.png", "e041.png",
    // "e042.png", "e043.png", "e044.png", "e045.png", "e046.png",
    // "e047.png", "e048.png", "e049.png", "e050.png", "e051.png",
    // "e052.png", "e053.png", "e054.png", "e055.png", "e056.png",
    // "e057.png", "e058.png", "e059.png", "e060.png", "e061.png",
    // "e062.png", "e063.png", "e064.png", "e065.png", "e066.png",
    // "e067.png", "e068.png", "e069.png", "e070.png", "e071.png",
    // "e072.png", "e073.png", "e074.png", "e075.png" };

    /**
     * 根据图片名获取ID,并生成图片对象
     *
     * @return ss
     */
    public static SpannableString getImg(Context context, String faceId) {
        SpannableString ss = new SpannableString("<f" + faceId + ">");
        // SpannableString ss = new SpannableString("[_nl_qqImg]" + faceId+
        // ".gif[/_nl_qqImg]");
        Drawable d = context.getResources().getDrawable(picture[Integer.parseInt(faceId)]);
        d.setBounds(0, 10, d.getIntrinsicWidth(), d.getIntrinsicHeight() + 10);
        ImageSpan imgSpan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        ss.setSpan(imgSpan, 0, ss.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

}
