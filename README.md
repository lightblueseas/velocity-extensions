# Overview

<div align="center">

[![Build Status](https://travis-ci.com/lightblueseas/velocity-extensions.svg?branch=master)](https://travis-ci.com/lightblueseas/velocity-extensions) 
[![Coverage Status](https://coveralls.io/repos/github/lightblueseas/velocity-extensions/badge.svg?branch=develop)](https://coveralls.io/github/lightblueseas/velocity-extensions?branch=master)
[![Open Issues](https://img.shields.io/github/issues/lightblueseas/velocity-extensions.svg?style=flat)](https://github.com/lightblueseas/velocity-extensions/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/velocity-extensions/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.alpharogroup/velocity-extensions)
[![Javadocs](http://www.javadoc.io/badge/de.alpharogroup/velocity-extensions.svg)](http://www.javadoc.io/doc/de.alpharogroup/velocity-extensions)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)

</div>

The velocity-extensions project holds several extensions for the velocity library.

If you like this project put a ⭐ and donate

## License

The source code comes under the liberal MIT License, making velocity-extensions great for all types of applications.

## Maven dependency

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~de.alpharogroup~velocity-extensions~~~) for latest snapshots and releases.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of velocity-extensions:

Than you can add the dependency to your dependencies:

	<properties>
			...
		<!-- VELOCITY-EXTENSIONS version -->
		<velocity-extensions.version>1.4</velocity-extensions.version>
			...
	</properties>
			...
		<dependencies>
			...
			<!-- VELOCITY-EXTENSIONS DEPENDENCY -->
			<dependency>
				<groupId>de.alpharogroup</groupId>
				<artifactId>velocity-extensions</artifactId>
				<version>${velocity-extensions.version}</version>
			</dependency>
			...
		</dependencies>
	
			
## gradle dependency

You can first define the version in the ext section and add than the following gradle dependency to 
your project `build.gradle` if you want to import the core functionality of velocity-extensions:

define version in file gradle.properties
```
velocityExtensionsVersion=1.4
```

or in build.gradle ext area

```
ext {
			...
    velocityExtensionsVersion = "1.4"
			...
}
```

and than add the dependency to the dependencies area

```
dependencies {
			...
    implementation("de.alpharogroup:velocity-extensions:$velocityExtensionsVersion")
			...
}
```
	
## Semantic Versioning

The versions of velocity-extensions are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

		


## Want to Help and improve it? ###

The source code for velocity-extensions are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [lightblueseas/velocity-extensions/fork](https://github.com/lightblueseas/velocity-extensions/fork)

To share your changes, [submit a pull request](https://github.com/lightblueseas/velocity-extensions/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the velocity-extensions developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/lightblueseas/velocity-extensions/issues).

## Note

No animals were harmed in the making of this library.

# Donations

If you like this library, please consider a donation through paypal: <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=B37J9DZF6G9ZC" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" border="0" />
</a>

or over bitcoin or bitcoin-cash with:

36JxRRDfRazLNqUV6NsywCw1q7TK38ukpC

or over ether with:

0x588Aa02De98B1Ef70afeDC3ec5290130a3E5e273

or over flattr: 
<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Flightblueseas%2Fvelocity-extensions" target="_blank">
<img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" border="0" />
</a>
