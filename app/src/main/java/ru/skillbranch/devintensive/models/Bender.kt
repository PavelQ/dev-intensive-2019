package ru.skillbranch.devintensive.models

import android.util.Log

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String =
        when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val (validityResult, validityText) = checkAnswerValidity(answer)
        if(!validityResult){
            Log.d("M_Bender.kt","listenAnswer incorrect: $validityResult, $validityText")
            return "$validityText\n${question.question}" to status.color
        }
        if (question.answers.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            return "Отлично - ты справился\n${question.question}" to status.color
        } else {
            if (status == Status.CRITICAL) {
                status = Status.NORMAL
                question = Question.NAME
                return "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
                status = status.nextStatus()
                return "Это неправильный ответ\n${question.question}" to status.color
            }
        }
    }

    fun checkAnswerValidity(answer: String): Pair<Boolean, String?> {
//        Log.d("M_Bender.kt","checkAnswerValidity")
        return when (question) {
            Question.NAME -> Pair(answer.isNotEmpty() && answer.first().isUpperCase(), "Имя должно начинаться с заглавной буквы")
            Question.PROFESSION -> Pair(answer.isNotEmpty() && answer.first().isLowerCase(), "Профессия должна начинаться со строчной буквы")
            Question.MATERIAL -> Pair(!answer.contains(Regex("\\d")), "Материал не должен содержать цифр")
            Question.BDAY -> Pair(answer.matches(Regex("\\d*")), "Год моего рождения должен содержать только цифры")
            Question.SERIAL -> Pair(answer.matches(Regex("\\d{7}")), "Серийный номер содержит только цифры, и их 7")
            Question.IDLE -> Pair(true, null)
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {

                NORMAL
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question {
                return PROFESSION
            }
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question {
                return MATERIAL
            }
        },
        MATERIAL("Из чего я сделан?", listOf("метал", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question {
                return BDAY
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question {
                return SERIAL
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question {
                return IDLE
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question {
                return IDLE
            }
        };

        abstract fun nextQuestion(): Question
    }
}