
  

# Welcome to my blog!

  

  

Hai, i am Steve and i am an Android developer. I write a base sample code for everyone who wanted to study Android programming. We also offering free course to everyone who wanted to learn Android programming mainly through the community that i run named Yogyakarta Developer Community in Indonesia. This base learning code will help you through your journey learning Android app. Feel free to learn and using it, and to hangout with us in Yogyakarta Developer Community.

  

  

# Journey Start

  
# But before you go,
# YOU ARE FREE TO USE THIS CODE BASE
  

Here is what you will go through.

  

  

## Presentation

  

  

There is bunch of technology you can choose to build presentation. There is two main thing of building presentation.

  

1. Build tool

  

Such XML layouting or Jetpack Compose for the newest version. There is codebase version using Jetpack Compose as base presentation [here (soon)]() .

  

2. Theme

  

We suggest to use Material Design, as is what we are using in this code base. It is fine, robust, and progressing.

  

  

You will learn about how to

  

1. Build presentation,

  

2. Doing navigation

  

3. Activity

  

4. Fragment

  

5. View Model

  

6. and the Android Lifecycle.

  

  

## Data Operation

  

  

Flow data of this code base following "Offline first" but for easy learning i am also including online first data flow. There is two main technology that we will learn in this codebase.

  

  

1. **Retrofit**, a library for network operation.

  

2. **Room database**, for local database operation.

  

  

Also you will learn about

  

1. Flow data using **StateFlow**

  

2. Data Convertion using **GSON**

  

  

## Architecture

  

See: https://miro.com/app/board/uXjVOK7eOX4=/?share_link_id=499372928469
1. **Clean Architecture + MVVM**
2. **Multiple Activity Multiple Fragment**
3. **Exclusive Presentation**
4. **Activity Channel**
5. **Inherited Data Model**
6. **Multi module**

  

  

## Principles

### THE FIVE PRINCIPLES

by stefanus.ayudha@gmail.com (me)

  

1. **Stable**

Use robust principles. Robust principle is art of best practice.

Every decision or argument taken or given without any base Principle supporting it, is consider **EMOTIONAL** decision or **EMOTIONAL** argument , is always bad decision or argument.

Most people think principle is not an urgent, that is wrong. Principle is the same as Technology. Using Old Principle and Old Tech is consider the same, you will facing same big problem by ignoring new principle. Mainly because principle work at very base of the App to the development philosophy all to the final output product.
Same with technology, principle can also be obsolete and being replaced with a new one. So **USE IT!**

  

2. **Distribute able**

Use standardised data structure and mechanism for great patch ability. Creativity that is not working with the system is garbage.

  

3. **Maintainable**

Clean code, SOLID, separate concern, Representative Coding, anything that obey Robust Principle used to be Maintainable.

  

4. **Scaleable**

Head on the future. Object Oriented, Think of scalability. Everything need to scale. There is only two option. 1. Stop Service. 2. Be Scalable. you can only chose one.

  

**Bad Scalability Example :**

```

{

user: "1029301930198192" // user id

}

```

That code above is bad for scalability, since we using "user" keyword which is and object keyword to store primitive data such string id. Now if we need to store another property of the user like so :

```

{

user: {

id: "1029301930198192" // user id

name: "User 1"

}

}

```

we have to shutdown the previous service.

  

You need to define property wisely, use primitive keyword to store primitive property and object keyword to store object.

**Correct example :**

```

{

user: {

id: "1029301930198192" // user id

}

}

```

now, whenever we need to scale the object we can put the property right away without shutting down existing service like so:

```

{

user: {

id: "1029301930198192" // user id

name: "User 1"

}

}

```

  
  

5. **Optimisable** : Making **Most OPTIMISE** code is stupid. Make it **OPTIMISE-ABLE** instead.

  

## Thats it until i know what to write in this README file
