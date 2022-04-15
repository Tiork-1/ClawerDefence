import sys
import re       # 正则表达式
import urllib.request,urllib.error   # 制定url，获取网页数据
from bs4 import BeautifulSoup  #网页解析，获得数据
import xlwt     # excel操作
import sqlite3 # 数据库操作

# 指定访问的页面
url = "http://qiuxinhan.top:8082/blogs/15361276"

# 得到一个指定url的网页内容，当getBody参数设置为True，只返回body，否则返回整个文档
def askUrl(url,getbody):
    head = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537..36 Edg/94.0.992.38"
    }
    request = urllib.request.Request(url=url,headers=head)
    html = ""
    try:
        response = urllib.request.urlopen(request)
        html = response.read().decode("utf-8")
    except Exception as e:
        print("Error when get response")
        if hasattr(e,"code"):
            print(e.code)
        if hasattr(e,"reason"):
            print(e.reason)

    if getbody==True:
        bs = BeautifulSoup(html, "html.parser")
        return bs.body

    return html

# 消除黑名单，因为上一次的操作会让本ip进入黑名单，所以这个操作会消除黑名单
askUrl("http://qiuxinhan.top:8082/cancelBlack",getbody=False)

# 获取一个页面
res = askUrl(url,getbody=False)

print(res)
