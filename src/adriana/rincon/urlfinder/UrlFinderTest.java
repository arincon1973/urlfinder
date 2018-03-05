package adriana.rincon.urlfinder;

import static org.junit.Assert.*;
import java.util.*;
import java.io.File;
import java.net.URI;

import java.net.URL;

/**
 * Created by v671078 on 3/4/18.
 */
public class UrlFinderTest {
    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void parseUrls() throws Exception {
        UrlFinder urlFinder = new UrlFinder();
        List<String> result;
        result = urlFinder.parseUrls("xyz");
        assertEquals(result.size(), 0);

        result = urlFinder.parseUrls("your device.\",\"IEINSTALLER_PRIVACY_TERMS\":\"<a href='{termsUrl}' target='_blank'>Yahoo Terms</a> and <a href='{privacyUrl}' target='_blank'>Privacy Policy</a>");
        assertEquals(0, result.size());

        result = urlFinder.parseUrls("<a href=\"https://www.yahoo.com/\" class=\"C(#fff) Fz(13px)\"   data-ylk=\"slk:home;t5:home;cpos:1;\" tabindex=\"1\"><i class=\"Pos(a) Fz(15px) Start(10px) T(-2px) Icon-Fp2 IconHome\"></i>Home</a>");
        assertEquals(1, result.size());

        result = urlFinder.parseUrls ("                <a href=\"https://www.flickr.com/\" class=\"C(#fff) Fz(13px)\"   data-ylk=\"slk:fl");
        assertEquals(1, result.size());

        result = urlFinder.parseUrls ("<li class=\"ActionComments D(ib) O(n) Pos(r) Lh(1) Mt(0)! Va(t)\" data-cmntnum=\"1601\"><a href=\"https://www.yahoo\"");
        assertEquals(1, result.size());

        result = urlFinder.parseUrls("<div class=\"App-Bd\"> <div class=\"App-Main\" data-region=\"main\"> <div class=\"js-applet-view-container-main\">  <h2 class=\"Fz(15px) Fw(b) Mb(7px)\"><a href=\"https://view.yahoo.com/?utm_source=yahoo&amp;utm_medium=referral&amp;utm_campaign=fpmodule\" class=\"Td(n) C(#000)\"   data-ylk=\"subsec:Yahoo View;itc:0;elm:hldn;\">The best TV for free</a></h2><div><a href=\"https://view.yahoo.com/show/saturday-night-live/episode/61071284/charles-barkley/?utm_source=yahoo&amp;utm_medium=referral&amp;utm_campaign=fpmodule\" class=\"Td(n) D(b) Cur(p) C(#000) C($m_blue):h C($m_blue):f Mt(15px) Mih(82px)\"   data-ylk=\"subsec:Yahoo View;cpos:1;itc:1;elm:img;\"><div class=\"Row\"><div class=\"Fl(start) W(50%) Pos(r)\"><img src=\"https://s2.yimg.com/uu/api/res/1.2/UyBchGXhS1jJEt1wVhTz7Q--/YXBwaWQ9eXRhY2h5b247aD0xNjQ7dz0yOTA7/https://ibdp.videovore.com/video/61071284?size=512x288\" class=\"Pos(r)\" alt=\"Saturday Night Live\" width=\"145\" height=\"82\"><div class=\"Pos(a) C(#fff) W(28px) H(28px) Bgi($orb) Bgz(ct) B(5px) Start(5px)\"><i class=\"Pos(a) Fz(12px) C(#fff) T(6px) Mstart(11px) Icon-Fp2 IconCorePlay\"></i></div></div><div class=\"Fl(start) W(50%)\"><div class=\"Pstart(8px)\"><div class=\"Fw(b) Fz(13px) Pb(2px)\">Saturday Night Live</div><div class=\"Fz(11px) C(#000) Pb(2px)\">Season 43 Episode 16</div><div class=\"Fz(11px) C($gray_dark) LineClamp(2,30px)\"\">Charles Barkley</div></div></div></div></a>");
        assertEquals(2, result.size());


    }

    @org.junit.Test
    public void parsePage() throws Exception {
        File file = new File(ClassLoader.getSystemResource("adriana/rincon/urlfinder/testInputs").getFile());
        ClassLoader classLoader = this.getClass().getClassLoader();
        String path = file.getAbsolutePath();

        UrlFinder urlFinder = new UrlFinder();
        List<String> result;
        result = urlFinder.parsePage("file://" + path);
        assertEquals(result.size(), 8);
    }
}