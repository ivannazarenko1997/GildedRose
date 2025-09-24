GildedRose application 

The Gilded Rose is an inventory management system for a small inn that buys and sells goods. Each item has two key values:

SellIn (days left to sell the item)

Quality (how valuable the item is)

The system updates these values daily, with rules that vary depending on the type of item:

Normal items degrade in Quality daily, and twice as fast after the sell-by date.

Aged Brie increases in Quality over time.

Backstage passes increase in Quality as the event approaches, but drop to 0 after the concert.

Sulfuras is legendary, with constant Quality (80) and no SellIn change.

Conjured items degrade in Quality twice as fast as normal items.

Quality is always between 0 and 50, except for Sulfuras. 

The task is to update the system to handle new rules,

especially for conjured items, without modifying the core Item class or Items property.

How to start 

Build the JAR

mvn clean package

This will generate a JAR file inside the target/ directory, e.g.:

java -jar target/gilded-rose-1.0-SNAPSHOT.jar