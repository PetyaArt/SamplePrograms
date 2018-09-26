
# coding: utf-8

# In[93]:


from math import *


# In[79]:


a = 0
b = 1
n = int(input())


# In[80]:


mas = [a + (b-a)/n*i for i in range(n+1)]
print(mas)


# In[81]:


def func(x):
    return(abs(x) * sin(x))


# In[82]:


masf = [func(x) for x in mas]
print(masf)


# In[83]:


def poly_lag(x, y_mas):
    s = 0
    for i in range(n+1):
        pr = 1
        for j in range(n+1):
            if (i != j):
                pr *= ((x - mas[j])/(mas[i] - mas[j]))
        s += (y_mas[i] * pr)
    return(s)


# In[84]:


pol_mas = [poly_lag(x, masf) for x in mas]
print(pol_mas)


# In[132]:


def delta(p, j):
    if p == 1:
        return (masf[j] - masf[j - 1])
    return (delta(p - 1, j) - delta(p - 1, j - 1))


# In[158]:


def ce(colc):
    if colc == 0:
        return masf[0]
    return(delta(colc, colc)/(factorial(colc) * (mas[1]-mas[0]) ** colc))


# In[159]:


def poly_nute(x):
    s = ce(0)
    pr = 1
    for i in range(n):
        pr *= (x - mas[i])
        s += (ce(i+1) * pr)
    return(s)


# In[160]:


nut_mas = [poly_nute(x) for x in mas]


# In[161]:


print(nut_mas)


# In[162]:


m = n * 4


# In[163]:


m


# In[164]:


mas_otv = [round((a + (b-a)/m*i), 10) for i in range(m+1)]
print(mas_otv)


# In[165]:


func_mas = [func(x) for x in mas_otv]


# In[166]:


mas_Lag = [poly_lag(x, masf) for x in mas_otv]


# In[167]:


mas_nute = [poly_nute(x) for x in mas_otv]
print(mas_nute)


# In[176]:


print("x" + " "*7 + "|" + " "*2 + "f(x)" + " "*19 + "|" + " " + " L(x)" + " "*19 + "|" + " " + "N(x)" )
print("--------|-------------------------|-------------------------|---------------------")
for i in range(m+1):
    print(f"{mas_otv[i]}" + " "*(8 - len(str(mas_otv[i]))) + "|" + " "*2 + f"{func_mas[i]}" + " "*(23 - len(str(func_mas[i]))) + "|" + " "*2 + f"{mas_Lag[i]}" + " "*(23 - len(str(mas_Lag[i]))) + "| " + f"{mas_nute[i]}")
    print("--------|-------------------------|-------------------------|---------------------")

