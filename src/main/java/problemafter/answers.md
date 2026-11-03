# Answers and tables - Imane Sindi
### 2. 
| Change request | Methods of `Order` to edit | What makes the change difficult |
|---|---|---|
| a. new extra (sauce, +3 MAD) and a dish with the same extra twice (double cheese) | `addDish` (new boolean parameter, new `if`), plus every caller in `Test` | Each extra is a boolean flag, so the signature grows with every extra. A boolean cannot say "twice". Extras are hard-wired into `Order`, which has nothing to do with them. |
| b. "Family menu" made of two student menus and a salad, with the discount applied at each level | `addCombo`, probably `print` and `total` | A combo is a flat `String[]` plus `double[]` of raw prices, so it cannot contain another combo. Names and prices live in parallel lists, so a nested structure does not fit. |
| c. new status READY between PREPARING and DELIVERED; a ready order can no longer be cancelled | `pay`, `startPreparing`, `deliver`, `cancel`, plus a new `markReady` | The status is a `String` checked by `if/else` in every method. Adding a state means re-reading all the conditions, and a typo such as `"PREPARNG"` still compiles. |
| d. loyalty service informed of every status change, and the customer can switch SMS off | `pay`, `startPreparing`, `deliver`, `cancel`, plus the fields | Notifications are hard-coded (`new SmsSender()`) and copy-pasted into four methods. Switching SMS off needs a flag tested in each one. |

### 3.
| Change request | Code smell | Pattern | Classes of the new design (role) |
|---|---|---|---|
| a. extras | Boolean flags, combinatorial explosion, behaviour added by editing a method | **Decorator** (structural) | `MenuItem` (Component), `Dish` (ConcreteComponent), `ExtraDecorator` (Decorator), `Cheese` / `Spicy` (ConcreteDecorators) |
| b. nested combos | Parallel lists and flat arrays, no part-whole hierarchy | **Composite** (structural) | `MenuItem` (Component), `Dish` (Leaf), `Combo` (Composite) |
| c. new status | `String` status with `if/else` repeated in every method | **State** (behavioral) | `Order` (Context), `OrderState` (State), `AbstractState`, `NewState` / `PaidState` / `PreparingState` / `DeliveredState` / `CancelledState` (ConcreteStates) |
| d. new notification | Hard-coded collaborators, duplicated calls | **Observer** (behavioral) | `Order` (Subject), `OrderListener` (Observer), `CustomerSms` / `KitchenScreen` (ConcreteObservers) |

### 6.
The outermost object is Spicy, it wraps Cheese which wraps Dish.

### 7.
A combo can contain a dish with cheese because both Combo and Cheese (decorator) are MenuItems. Combo.add() accepts any MenuItem, and a decorated dish is one.
We can write new Cheese(studentMenu) because Cheese wraps any MenuItem including the combo studentMenu. As for the pricing, we will add 5 MAD to the total price.
No it does not make sense to the restaurant, because while preparing the order it may be confusing either to consider adding cheese to the dish or just adding it separately ad a side dish.

### 8.
| Change request | Existing files opened | Comparison with question 2 |
|---|---|---|
| a. sauce, double cheese | **0**. Add a new `Sauce` class (+3 MAD), then write `new Cheese(new Cheese(dish))`. | Before: `addDish` and all its callers. |
| b. Family menu | **0**. Write `new Combo("Family menu").add(student1).add(student2).add(salad)`. The 10% is applied at each level because `getPrice` is recursive. | Before: `addCombo`, `print` and `total`. |
| c. READY status | **4**: `OrderState` and `AbstractState` (new `markReady`), `Order` (delegating method), `PreparingState` (`deliver` becomes `markReady`). New file `ReadyState` with `deliver`. `cancel` is refused for free by `AbstractState`. | Before: all four methods rewritten. The edits are now small and local, and no existing rule changes. This is the weakest gain, since a new action touches the interface. |
| d. loyalty service, SMS off | **0**. Add `LoyaltyService implements OrderListener`, then call `order.subscribe(...)` and `order.unsubscribe(sms)`. | Before: all four transition methods plus the fields. |

### 9.
#### Singleton for KitchenListener: 
  - A restaurant may have several screens, such as grill and drinks.
  - It is now just an OrderListener, injected with subscribe, so nothing needs global access. 
  - Singleton would also work against the dependency-inversion benefit we just gained. 
#### Builder for Order: 
  - Order has one required argument (customer), and items are added with add. Combo.add is already
