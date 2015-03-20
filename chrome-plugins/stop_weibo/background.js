
var g_setting = {
  interval: 60*60*1000,
  free_time: 5*60*1000,
};


var navigation_filters = {
  url: [
    { hostSuffix: 'weibo.com' }
  ]
};

// http://ww3.sinaimg.cn/mw1024/5fa3d0f8jw1e79om9d6twj20ez0qoq3r.jpg
var request_filters = {
  // urls: [ "*://weibo.com/*", "*://*.weibo.com/*" ],
  urls: [ "*://weibo.com/*" ],
  types: [ "main_frame", "sub_frame", "stylesheet", "script", "image", "object" ]
};

var g_last_visit_time = undefined;

// chrome.webNavigation.onCommitted.addListener(on_domcontent_loaded, navigation_filters);
chrome.webNavigation.onDOMContentLoaded.addListener(on_domcontent_loaded, navigation_filters);
// chrome.webNavigation.onBeforeNavigate.addListener(on_before_navigate, navigation_filters);
// chrome.webRequest.onBeforeRequest.addListener(on_before_request, request_filters, [ "blocking" ]);

function should_visit () {
  if (!g_last_visit_time) {
    console.log("first time, you lucky boy");
    g_last_visit_time = new Date();
  } else {
    var cur_time = new Date();
    var delta = cur_time.getTime() - g_last_visit_time.getTime();
    console.log(g_last_visit_time);
    console.log(cur_time);
    console.log(delta);
    if (delta < g_setting["free_time"]) {
      // should let it go
      console.log("still in break time");
    } else if (delta < (g_setting["free_time"] + g_setting["interval"])) {
      console.log("no you can't");
      // forbid it
      return false;
    } else {
      console.log("time to take a break");
      // update g_last_visit_time
      g_last_visit_time = cur_time;
    }
  }

  return true;
}

function on_before_navigate (data) {
  url = data['url'];
  if (!url) {
    console.log("no url is there, ignore");
    return;
  }
  console.log("[before navigate] url: " + url);
}

function on_domcontent_loaded (data) {
  console.log("on_domcontent_loaded(): " + data['url']);
  chrome.tabs.executeScript(null,
    { file: "content_script.js" },
    function () {
      chrome.tabs.sendMessage(data.tabId, {
        break_time: 5,
        study_time: 5
      });
    }
  );
}

function on_before_request (data) {
  url = data['url'];
  method = data['method'];
  if (!url || !method) {
    console.log("no url or no method is there, ignore");
    return;
  }

  console.log("[before request] method: " + method + " url: " + url);

  // don't filter POST, or user may lose their post
  if (method != "GET") {
    // return { cancel: true };
    return;
  }

  if (should_visit()) {
    return;
  } else {
    return { cancel: true };
  }
  // return { cancel: true };
  // return { redirectUrl: "http://www.google.com.hk" };
  // return { redirectUrl: "chrome-extension://__MSG_@@extension_id__/popup.png" };
}

