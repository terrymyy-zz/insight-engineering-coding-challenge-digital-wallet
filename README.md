# Table of Contents

1. [Summary] (README.md#summary)
2. [Details of Implementation] (README.md#details-of-implementation)
3. [Description of Data] (README.md#description-of-data)

##Summary

This project is for insight engineering program coding challenge. Details at: [InsightDataScience/digital-wallet] (https://github.com/InsightDataScience/digital-wallet).
This project has 3 features:
###Feature 1
When anyone makes a payment to another user, they'll be notified if they've never made a transaction with that user before.(1st-degree friend).

For feature 1, `HashMap<Integer, ArrayList<Integer>>` is used to store each new user id as the "Key", and his/her "1st-degree friend" is stored inside the `ArrayList<Integer>`. By checking every time if an user id is inside the "key set" or inside each current users' 1st-degree friend ArrayList can a transaction be determined whether unverified or trusted.

Note that, *here is a rule that larger ids would be stored in smaller ids, but not vice versa*. This will reduce space complexity and time complexity.

For feature1, time complexity and space complexity are O(n), O(n).
###Feature 2
Feature 2 is an extension of Feature 1. Not only the "1st-order friends" are searched, but the "friends of a friend" or "2nd-degree friends" are searched.

Data structure is the same, but the algoritm is more complicated. For each "1st-order friends" of id1, search their friends to find if there is id2. In addition

###Feature 3


###Other considerations and optional features

##Details of implementation

[Back to Table of Contents] (README.md#table-of-contents)

###Input


###Output


##Description of Data

[Back to Table of Contents] (README.md#table-of-contents)


##Writing clean, scalable and well-tested code
[Back to Table of Contents] (README.md#table-of-contents)
