*Traffic simulator*\
**Patrascoiu Ion - Radu, 336CC**

In implementarea temei am folosit scheletul pus la dispozitie, astfel ca in
interfata Intersection mi-am definit metoda **hadle** care primeste o entitate
**Car** si este suprascrisa in toate intersectiile care implementeaza interfata.
Implementarea metodei reprezinta efectiv rezolvarea fiecarui task. In rest,
clasele date in schelet au suferit mici modificari, prin 
adagarea tuturor cazurilor de citire/creare a intersectiilor.

*Task1:* Rezolvat in clasa **SimpleIntersection**, am afisat mesajul de sosire
al masinilor, am facut *sleep* cu timpul de asteptare al fiecarei masini, apoi
am afisat mesajul de plecare de pe loc al acestora.

*Task2:* Rezolvat in clasa **SimpleNRoundabout**, am creat metoda *build*, care
simuleaza un constructor, astfel am trimis datele in intersectie. Am folosit un
semafor, initializat cu numarul de masini care au voie in sensul giratoriu.
Dupa afisarea mesajului care semnaleaza ajungerea in intersectie, am facut
acquire, apoi am afisat mesajul de intrare. Am facut sleep pentru a simula
timpul petrecut de masini in sens, am afisat mesajul de iesire si am facut
release. Astfel, pot intra urmatoarele mașini.

*Task3:* Rezolvat in clasa **SimpleStrict1CarRoundabout**, este asemanator cu
task-ul anterior, doar ca acum voi avem o lista de semafoare, cu un singur
acquire posibil. O alta alternativa era sa fac un array boolean, care marca
cu true sau false daca se poate intra sau nu in intersectie.

*Task4:* Rezolvat in clasa **SimpleStrictXCarRoundabout**, am creat metoda
build, care creaza intersectia ce va avea, 3 bariere si o lista de semafoare.
**startBarrier** va fi initializata cu numarul total de masini, 
**enterInRoundaboutBarrier** si **exitBarrier** cu numarul maxim de masini care
pot intra intr-o runda in intersectie, iar fiecare semafor din lista cu numarul
maxim de maxini care pot intra intr-o intersectie la o runda. Mai intai am
afisat mesajul prin care masinile ajung la intersectie. Fac await cu pentru a
astepta sosirea tuturor masinilor. Pentru fiecare directie fac acquire pe
semafor si afisez mesajul pentru a marca masina selectata sa intre in
intersectie. Apoi, astept sa iasa toate masinile din runda anterioara, facand
await pe o alta bariera. Dupa asta, afisez mesajul pentru intrarea masinilor in
intersectie, fac un al 3-lea await, pentru a sincroniza intrarea in sens a
masinilor. Fac sleep, pentru a simula timpul petrecut in giratoriu, afisez
mesajul de iesire din intersectie dau release.

*Task5:* Rezolvat in clasa **SimpleMaxXCarRoundabout**, am creat metoda build,
care creaza intersectia cu o lista de semafoare initializate cu numarul maxim
de masini care pot intra de pe fiecare directie intr-o runda. Task-ul este
asemanator cu 3. Afisez primul mesaj, fac acquire, afisez al doilea mesaj, fac
sleep, afisez al treilea mesaj si fac release.

*Task6:* Rezolvat in clasa **PriorityIntersection**, am folosit o coada thread
safe(BlockingDeque), pentru a pastra ordinea de intrare. Am luat 2 cazuri:
primul, in care masinile au prioritate de trecere, astfel ca afisez mesajul
de intrare intersectie. Apoi, incrementez o variabila atomica, pentru a fi
sigur ca un singur thread modifica acea valorea, la un moment dat. Fac sleep
pentru 2 secunde, afisez mesajul de iesire din intersectie si decrementez
variabila precizata mai sus, pentru a marca faptul ca a mai iesit o masina cu
prioritate din intersectie. Apoi, verific daca mai am masini cu prioritate in
intersectie si daca am in coada masini fara prioritate. Daca ambele conditii
sunt indeplinite, atunci se scot masinile fara prioritate din coada si se
afiseaza mesajele corespunzatoare. Al doilea caz, in care masinile nu au
prioritate, astfel se afiseaza mesajul initial si se verifica daca in
intersectie exista masini cu prioritate. Daca da, atunci masinile pot intra
si se afiseaza mesajul de intrare, altfel, se pun masinile in coada.

*Task7:* Rezolvat in clasa **Crosswalk**, nu a mai fost nevoie de metoda build.
Am folosit clasa primita in schelet **Pedestrians**, astfel am initializat cu
"red" un string *color*, care retine la fiecare pass, daca pietonii trec sau nu,
adica daca masinile au rosu sau verde. Cat timp trecerea pietonilor nu este
finalizata, se schimba culoarea precizata mai sus, verific daca campul *color*,
adaugat de mine in clasa **Car**, este null, adica daca este prima trecere sau
daca a existat o schimbare de culoare. Daca una dintre conditii este adevarata,
atunci campul din Car este modificat si se afiseaza mesajul corespunzator.

*Task8:* Rezolvat in clasa **SimpleMaintenance**, am folosit, din nou metoda
build, care imi creaza 2 semafoare, unul pentru fiecare banda de mers si o
bariera, utilizata pentru sincronizare(initializata cu numarul de masini care
pot trece pe o banda). Asfel, la inceput se afiseaza mesajul corespunzator,
apoi iau 2 cazuri, daca directia de start a masinilor este 0, atunci se face
acquire pe semaforul drumului cu prioritate, se face await, pentru a astepta sa
treaca toate cele X masini de pe sens. Altfel, daca s-ar face release inainte,
conditia problemei nu ar mai fi respectata, pentru masinile din sens opus ar
incepe sa plece. Dupa sincronizare, am afisat mesajul corespunzator si am facut
release pe semaforul fara prioritate, marcand astfel faptul ca din sens opus
pot pleca masini. Analog, pentru al doilea caz, cand directia de start este 1.

*Task9:* Pass :))

*Task10:* Rezolvat in clasa **RailRoad**, am creat intersectia cu ajutorul
metodei *build*, care initializeaza 2 cozi sincronizate si o bariera. Mai intai,
am afisat mesajul corespunzator, apoi am luat 2 cazuri(sincronizate), unul
pentru fiecare directie posibila: 0 si 1. Daca este 0, atunci se pune masina
in coada 0, altfel in coada 1. Sincronizam masinile cu ajutorul barierei si
afisam mesajul de ridicare al acesteia(afisarea se face o singura daca cu
ajutorul variabilei volatile **ok**). Apoi, pentru fiecare coada, se verifica
daca este goala, iar in caz contrat se afiseaza mesajul corespunzator si se scot
masinile din structura de date.


