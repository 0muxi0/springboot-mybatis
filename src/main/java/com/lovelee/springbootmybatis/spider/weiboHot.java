package com.lovelee.springbootmybatis.spider;

import com.lovelee.springbootmybatis.dao.UserDao;
import com.lovelee.springbootmybatis.dao.miniHotweiboDao;
import com.lovelee.springbootmybatis.model.miniHotweibo;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2018/7/15.
 */
public class weiboHot implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public static void main(String[] args) {
        Spider.create(new weiboHot())
                .addUrl("https://m.weibo.cn/api/container/getIndex?containerid=102803_ctg1_8999_-_ctg1_8999_home")
                .run();
    }
    public Site getSite() {
        return site;
    }

    private miniHotweiboDao miniHotweiboDao;

    public void process(Page page) {
        List<String> urls = new ArrayList<String>();
        for (int i = 2; i < 30; i++) {
            urls.add("https://m.weibo.cn/api/container/getIndex?containerid=102803_ctg1_8999_-_ctg1_8999_home&page="
                    + i);

        }

        page.addTargetRequests(urls);
        String pagestring = page.getRawText();
        if (pagestring.length()>100) {

            List<String> links = new ArrayList<String>();
            links = new JsonPathSelector("$.data.cards[*].scheme").selectList(pagestring);

            List<String> username = new ArrayList<String>();
            username = new JsonPathSelector("$.data.cards[*].mblog.user.screen_name").selectList(pagestring);

            List<String> content = new ArrayList<String>();
            content = new JsonPathSelector("$.data.cards[*].mblog.text").selectList(pagestring);
            for (int i = 0; i < links.size(); i++) {
//                System.out.println(username.get(i));
//                System.out.println(links.get(i));
//                System.out.println(content.get(i));
                miniHotweibo tmp = new miniHotweibo();
                tmp.setUsername(username.get(i));
                tmp.setLinks(links.get(i));
                tmp.setContent(content.get(i));
                miniHotweiboDao.insert(tmp);
            }
        }
    }
}
