package com.example.a7minutesworkout

object Constants {
    fun defaultExcerciseList(): ArrayList<ExcerciseModels>{
        val excerciseList = ArrayList<ExcerciseModels>()

        val jumpinJacks= ExcerciseModels(1,"Jumping Jacks", R.drawable.ic_jumping_jacks, false , false)
        excerciseList.add(jumpinJacks)


        val wallSit = ExcerciseModels(2,"Wall Sit", R.drawable.ic_wall_sit,false, false)
        excerciseList.add(wallSit)

        val pushUp = ExcerciseModels(3, "Push Up", R.drawable.ic_push_up, false, false)
        excerciseList.add(pushUp)

        val abdominalCrunch =
            ExcerciseModels(4, "Abdominal Crunch", R.drawable.ic_abdominal_crunch, false, false)
        excerciseList.add(abdominalCrunch)

        val stepUpOnChair =
           ExcerciseModels(
                5,
                "Step-Up onto Chair",
                R.drawable.ic_step_up_onto_chair,
                false,
                false
            )
        excerciseList.add(stepUpOnChair)

        val squat =ExcerciseModels(6, "Squat", R.drawable.ic_squat, false, false)
        excerciseList.add(squat)

        val tricepDipOnChair =
           ExcerciseModels(
                7,
                "Tricep Dip On Chair",
                R.drawable.ic_triceps_dip_on_chair,
                false,
                false
            )
        excerciseList.add(tricepDipOnChair)

        val plank =ExcerciseModels(8, "Plank", R.drawable.ic_plank, false, false)
        excerciseList.add(plank)

        val highKneesRunningInPlace =
           ExcerciseModels(
                9, "High Knees Running In Place",
                R.drawable.ic_high_knees_running_in_place,
                false,
                false
            )
        excerciseList.add(highKneesRunningInPlace)

        val lunges =ExcerciseModels(10, "Lunges", R.drawable.ic_lunge, false, false)
        excerciseList.add(lunges)

        val pushupAndRotation =
           ExcerciseModels(
                11,
                "Push up and Rotation",
                R.drawable.ic_push_up_and_rotation,
                false,
                false
            )
        excerciseList.add(pushupAndRotation)

        val sidePlank =ExcerciseModels(12, "Side Plank", R.drawable.ic_side_plank, false, false)
        excerciseList.add(sidePlank)

        return excerciseList
    }
}