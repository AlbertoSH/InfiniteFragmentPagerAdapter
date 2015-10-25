# Infinite Fragment Pager Adapter

[ ![Download](https://api.bintray.com/packages/albertosh/maven/infininite-fragment-pager-adapter/images/download.svg) ](https://bintray.com/albertosh/maven/infininite-fragment-pager-adapter/_latestVersion)

## Overview

**InfiniteFragmentPagerAdapter** allows you to implement easily a FragmentPagerAdapter that has infinite fragments. You only need to implement how to create a single page.
It also maintains the state of next and previous fragments so it swipes nicely


## Usage
Subclass `InfiniteFragmentPagerAdapter` and implement the `getPage(index)` method.
The `Fragment` returned **must** implement the `InfiniteFragmentPagerFragment` interface. Alternately, your `Fragment` class can extend `InfiniteFragmentPagerFragmentImpl` and all the work will be done for you!

Check the sample for a more detailed view.

##License
 
    Copyright 2015 Alberto Sanz

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
