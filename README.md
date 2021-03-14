# Pay_The_Bills_9000 
(Name subject to change soon)

(Please open the file before reading as GitHub formatting is bad or idk how to use it yet)

Android Car Payment/Maitanance Reminder App - By Mircea Dobre - 293117
 
 I want to create an Android app that reminds me to pay the bills for my car(green tax, insurance and so on), get a new syn and also remembers me to do car maitanance like changing oil, fiters at a given time/interval. The motivation behind this app is a friend who didn't get any messages or bills from skat to pay his green tax at time and lost his license plates as a result and a hefty fine besides not having a car for a while.
 
 -#- MoSCoW Requirements: -#-
 
 @Must have:
 -Be able to insert manualy - service interval/entry
                            - syn interval/entry
                            - green tax interval and sum/entry
                            - insurance interval/entry
 
 -Service history
 -Syn history
 -Green tax history
 -Insurance history
 -Own car information panel
                            
 -Push notification(1 week before and and the last 3 days until is due if not set as done) 
                    when
                         - service is due
                         - syn is due 
                         - green tax is due
                         - insurance is due
 
 @Should have:
 -Mail notification when action is due withing 30 days
 -Timing belt/water pump/accesory belt change interval and history
 -The car information panel could contain OIL/FILTER/part parts numbers for service
 -Export the history data from the app as a pdf/txt file and be able to share it via email/whatsapp or something similar
 
 @Could have:
 -Be able to see car info from skat by it's license plate number and import syn and green tax data automaticly
 -Multiple car support
 -Have the data stored on a database and also have a log in

 @Won't have:
 -Low fuel notification as it requires an OBD device that should connect to the phone via Bluetooth
 -Automaticly get the KM from the car(same reason as first one)
 
