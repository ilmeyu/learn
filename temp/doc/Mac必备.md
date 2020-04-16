## 破解软件

> macOS 10.15 Catalina xxx.app已损坏，无法打开，你应该将它移到废纸篓解决方法
> [原文链接](https://macwk.com/article/mac-catalina-1015-file-damage)

更新macOS 10.15 Catalina后，很多在10.14上可以使用的App都会提示提示【xxx已损坏，无法打开，你应该将它移到废纸篓解决办法】，
哪怕你在【安全与隐私 》 通用】中已经开启了“任何来源”，但还是会这样提示，下边下面就教大家如何修复。

### 准备

先打开 `系统偏好设置 -> 安全与隐私 -> 通用` 选项卡，检查是否已经启用了 `任何来源` 选项。如果没有启用，先点击左下角的小黄锁图标解锁，
然后选中`任何来源`。如果没有这个选项，我们打开`终端`，输入以下命令 (可以通过点击屏幕右上角的搜索图标，输入`终端`快速运行)：

![打开终端](../img/mac-catalina-10.15-file-damage-02.jpg)

然后按下键盘的回车键（return），输入密码，再按回车键，完成。

> 输入命令回车后会看见个 password 后面还有个钥匙图标，在钥匙图标后面输入你自己电脑解锁密码
>（输入的时候不显示你输入的密码，感觉就是输入不了东西一样，也不用管，凭感觉输入完正确解锁密码后按回车键）

好了，现在回到 `系统偏好设置 -> 安全性与隐私 -> 通用` 里，就会发现已选中`任何来源`选项了。

![开启任何来源](../img/mac-catalina-10.15-file-damage-01.png)

> 一般执行完命令会默认选中的，如果没有选中，解锁一下选中就可以了！

到这里一般情况下应用都可以运行了，特别是 macOS 10.14 及以下系统

但是 macOS 10.15x 系统对于未签名的应用又进一步收缩了权限，众所周知破解软件基本上不会进行签名的，
特别是所以当我们在 10.15.x 系统上运行破解软件的时候可能还会提示`xxx.app已损坏`，
不过没关系，我们通过命令绕过苹果的公证 Gatekeeper 就可以了。

### 绕过公证

打开终端，输入以下命令：

```shell script
sudo xattr -rd com.apple.quarantine /Applications/xxxxxx.app
```

将上面的 `xxxxxx.app` 换成你的App名称，比如 `Sketch.app`

```shell script
sudo xattr -rd com.apple.quarantine /Applications/Sketch.app
```

或者复制以下命令粘贴到终端后

```shell script
sudo xattr -rd com.apple.quarantine 
```

打开Finder（访达），点击左侧的 `应用程序`，将应用拖进终端中，然后按键盘的回车键（return），输入密码，再按回车键，完成。

> 注意 quarantine 后面必须有个空格

![拖动app到终端](../img/mac-catalina-10.15-file-damage-03.jpg)

好了再看一下是不是可以打开APP了！