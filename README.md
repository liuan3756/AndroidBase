# AndroidBase

    本库使用了AndroidX、ViewBinding、Retrofit、RxJava2、Glide。
    主要为提高开发小中规模app的效率。基类提供足够多的扩展性，封装足够多的工具类。
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

	dependencies {
            implementation 'com.github.liuan3756:AndroidBase:$version'
    }