package com.cc.testchar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private WebView wv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StringBuffer c=null;


        String s=" \n <p>\n   【药品名称】 \n </p> \n  \n  <p> 通用名称：小儿喜食糖浆 </p> \n  \n \n \n <p>\n   【成份】 \n </p> \n  \n  <p> 六神曲(炒) 77g 枳壳(炒) 39g 白术(炒) 39g山楂 59g 稻芽(炒) 192g 麦芽(炒) 192g</p> \n  \n \n \n <p>\n   【性状】 \n </p> \n  \n  <p> </p> \n  <p>本品为棕褐色的粘稠液体；味甜、微苦。</p> \n  <p></p> \n  \n \n \n <p>\n   【功能主治】 \n </p> \n \n   健脾，消食，化积。用于治疗小儿单纯性消化不良，食欲不振及消化不良引起的腹泻。 [展开] \n  \n  \n  <p>健脾，消食，化积。用于治疗小儿单纯性消化不良，食欲不振及消化不良引起的腹泻。</p> [收起] \n  \n \n \n <p>\n   【规格】 \n </p> \n  \n  <p> 每瓶装100毫升 </p> \n  \n \n \n <p>\n   【用法用量】 \n </p> \n \n   口服。一至五岁一次3～5毫升，五岁以上一次10～15毫升，周岁以内酌减；一日3次。 [展开] \n  \n  \n  <p>口服。一至五岁一次3～5毫升，五岁以上一次10～15毫升，周岁以内酌减；一日3次。</p> [收起] \n  \n \n \n <p>\n   【注意事项】 \n </p> \n  \n  <p>1&nbsp; 忌食生冷、辛辣食物。 2&nbsp; 节制饮食，不要偏食。 3&nbsp; 服药一周后症状无明显改善或出现不良反应时应向医师咨询。 4&nbsp; 按照用法用量服用，婴儿用量可咨询医师。 5&nbsp; 对本品过敏者禁用，过敏体质者慎用。 6&nbsp; 本品性状发生改变时禁止使用。 7&nbsp; 儿童必须在成人监护下使用。 8&nbsp; 请将本品放在儿童不能接触的地方。 9&nbsp; 如正在使用其他药品，使用本品前请咨询医师或药师。</p> [展开] \n  \n  \n  <p></p> \n  <p>1&nbsp; 忌食生冷、辛辣食物。 2&nbsp; 节制饮食，不要偏食。 3&nbsp; 服药一周后症状无明显改善或出现不良反应时应向医师咨询。 4&nbsp; 按照用法用量服用，婴儿用量可咨询医师。 5&nbsp; 对本品过敏者禁用，过敏体质者慎用。 6&nbsp; 本品性状发生改变时禁止使用。 7&nbsp; 儿童必须在成人监护下使用。 8&nbsp; 请将本品放在儿童不能接触的地方。 9&nbsp; 如正在使用其他药品，使用本品前请咨询医师或药师。</p> \n  <p></p> [收起] \n  \n \n \n <p>\n   【药物相互作用】 \n </p> \n  \n  <p>本品为小儿厌食类非处方药药品。如与其他药物同时使用可能会发生药物相互作用，详情请咨询医师或药师。</p> [展开] \n  \n  \n  <p></p> \n  <p>本品为小儿厌食类非处方药药品。如与其他药物同时使用可能会发生药物相互作用，详情请咨询医师或药师。</p> \n  <p></p> [收起] \n  \n \n \n <p>\n   【药理作用】 \n </p> \n  \n  <p>健脾，消食，化积。</p> [展开] \n  \n  \n  <p></p> \n  <p>健脾，消食，化积。</p> \n  <p></p> [收起] \n  \n \n \n <p>\n   【贮藏】 \n </p> \n  \n  <p> </p> \n  <p></p> \n  <p>密封。</p> \n  <p></p> \n  <p></p> \n  \n \n \n <p>\n   【包装】 \n </p> \n  \n  <p> </p> \n  <p>每瓶装100ml；15ml 100ml &nbsp;&nbsp;</p> \n  <p></p> \n  \n \n \n <p>\n   【批准文号】 \n </p> \n  \n  <p> </p> \n  <p>国药准字Z42020654</p> \n  <p></p> \n  \n \n \n <p>\n   【批准日期】 \n </p> \n  \n  <p> </p> \n  <p>2010-9-30 0:00:00</p> \n  <p></p> \n  \n \n \n <p>\n   【生产企业】 \n </p> \n  \n  <p> 企业名称：武汉鄂中药业有限公司</p> \n  \n  \n  <p> 生产地址：湖北省武汉市黄陂区川龙大道特1号</p> \n  \n  \n  <p> 联系电话：027-51788891 </p> 如有问题可与生产企业联系 \n  \n \n \n <p>\n   【产地】 \n </p> \n  \n  <p> </p> \n  <p></p> \n  <p></p> \n  \n";
        StringBuffer f = getCurrent(s);
        wv_show=(WebView) findViewById(R.id.wv_show);
        WebSettings webSettings = wv_show.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setTextZoom(300);

        wv_show.loadData(f.toString(),
                "text/html; charset=utf-8", "utf-8");

    }

    @NonNull
    private StringBuffer getCurrent(String s) {
        StringBuffer f = new StringBuffer(s);
        char  a='[';
        int index=-1;
        int  count=0;
        List<Integer> b=new LinkedList<>();
        for(int i=0;i<s.length();i++) {
            if ((s.indexOf(a, index + 1)) != -1) {
                index = s.indexOf(a, index + 1);
                b.add(index);
                count++;
            }
        }
        for (int i=count/2;i>0;i--){
            f.delete(b.get(2*i-2),b.get(2*i-1)+4);
        }
        return f;
    }
}
