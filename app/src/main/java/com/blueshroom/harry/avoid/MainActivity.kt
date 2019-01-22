package com.blueshroom.harry.avoid

import android.content.Context
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.grid_entry.view.*
import android.media.AudioManager

private const val TAG : String = "void_MAIN_ACTIVITY"

class MainActivity : AppCompatActivity() {

    // Global game instance
    lateinit var game : Game

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set Audio Manager
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0)

        // Hide action bar
        supportActionBar?.hide()

        // Hide the UI
        hideUI()

        // Create game
        game = Game(this)

        /** ACTIVE TEST **/
        transitionTest()
        /** ACTIVE TEST **/
    }

    // Save user progress via writing to files
    override fun onStop()
    {
        super.onStop()

        Log.d(TAG,"onStop called")

        // Save the game data
        game.saveData()
    }

    // Activate immersive mode through flags
    private fun hideUI()
    {
        // Immersive mode
        window.decorView.systemUiVisibility = (
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    // Re-activate immersive mode on focus change
    override fun onWindowFocusChanged(hasFocus: Boolean)
    {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideUI()
    }

/********** MODIFIER FUNCTIONS **********/

    // Transition to a new Area, setting text, image, and actions
    fun transitionArea(transition : Transition)
    {
        // Get the area we are transitioning to
        val area = transition.areaTo

        // Save as current area
        game.setCurrentArea(area)

        // Here we see if an event happens in the throughArea
        // Fade? Text, tap to load all text,

        // UI stuff
        initUI(area, transition.getTransitionText())

    }

    // Set title, image, action button grid, and typewriter
    private fun initUI(area : Area, textStr : String)
    {
        // Set title
        titleView.text = area.name

        // Set image
        imageView.setImageResource(area.areaDrawId)

        // Set action button grid
        buttonGrid.adapter = CustomAdapter(this,area.generateActions())

        // Set and start typewriter
        typewriterView.text = textStr
        typewriterView.setCharacterDelay(75)
        typewriterView.animateText(typewriterView.text)
    }

    // Call initUI with game currentArea
    private fun initStartArea(game : Game)
    {
        initUI(game.getCurrentArea(),game.getCurrentArea().description)
    }

    // Refresh the action grid View
    private fun refreshActionGrid()
    {
        buttonGrid.adapter = CustomAdapter(this,game.getCurrentArea().generateActions())
    }

    // Safe new image in currentArea and modify image View
    private fun modifyCurrentAreaImage(imageId: Int)
    {
        game.getCurrentArea().areaDrawId = imageId
        imageView.setImageResource(game.getCurrentArea().areaDrawId)
    }

    // Animate new text in the typeWriter View
    private fun setAndAnimateNewText(newText : String)
    {
        typewriterView.text = newText
        typewriterView.setCharacterDelay(75)
        typewriterView.animateText(typewriterView.text)
    }

    // Play a sound using an ID param
    fun playSound(soundId : Int)
    {
        val soundPlayer = MediaPlayer.create(this,soundId)
        soundPlayer.start()
    }

    // Custom adapter class for buttonGrid
    class CustomAdapter(private val context : Context, val actions : MutableList<Action>) : BaseAdapter()
    {
        // Get number of elements
        override fun getCount(): Int { return actions.size }

        // Get item at index
        override fun getItem(index: Int): Any { return actions[index] }

        // Doesn't really do anything
        override fun getItemId(index: Int): Long { return index.toLong() }

        override fun getView(index: Int, convertView: View?, parent: ViewGroup?): View?
        {
            val cont = context as MainActivity
            var gridEntry : View? = null

            if(convertView == null) {
                // Get the action
                val action = actions[index]

                // Get the inflater
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                // Inflate the action into a 'grid_entry'
                gridEntry = inflater.inflate(R.layout.grid_entry, parent, false)

                // Set Button Text in entry
                gridEntry.gridButton.text = action.getActionText()

                // Set onClick
                gridEntry.gridButton.setOnClickListener {

                    /**
                     * Here we determine what the button does based on what the action is
                     */

                    var isText = false

                    when(action)
                    {
                        is TransitionAction ->
                        {
                            cont.transitionArea(action.transition)
                        }

                        is ModifierAction ->
                        {
                            val actionsTemp = cont.game.getCurrentArea().generateActions()

                            for(mod : Mod in action.mods)
                            {
                                // Do the specific modifier action
                                when(mod)
                                {
                                    // Change the image view
                                    is ImageMod ->
                                    {
                                        cont.modifyCurrentAreaImage(mod.getVal())
                                    }

                                    // Set and animate new text in the typewriter view
                                    is TextMod ->
                                    {
                                        cont.setAndAnimateNewText(mod.getVal())
                                        isText = true
                                    }

                                    // Remove all actions with the action ID set in the mod
                                    is RemoveActionMod ->
                                    {
                                        for(a : Action in actionsTemp)
                                        {
                                            if(a.getID() == mod.getVal())
                                            {
                                                actionsTemp.remove(a)
                                            }
                                        }
                                    }

                                    // Add a new action
                                    is AddActionMod ->
                                    {
                                        actionsTemp.add(mod.getVal())
                                    }

                                    // Remove this action
                                    is RemoveItselfMod ->
                                    {
                                       actionsTemp.remove(action)
                                    }

                                    // Play the sound
                                    is SoundMod ->
                                    {
                                        cont.playSound(mod.getVal())
                                    }

                                    // Set Flag
                                    is FlagMod ->
                                    {
                                        for(flagsetter : FlagSetter in mod.getVal())
                                        {
                                            cont.game.changeFlags(flagsetter)
                                        }
                                    }

                                }
                            }

                            // Change the description to the mod description
                            // if not previously set by a text mod
                            if(!isText)
                            {
                                cont.setAndAnimateNewText(action.descrText)
                            }

                            cont.game.getCurrentArea().actions = actionsTemp

                            // Refresh the action grid with new area
                            cont.refreshActionGrid()

                        } // Modifier Action

                        // Else log an error
                        else ->
                        {
                            Log.e(TAG, "Not a real Action: " + action.getID())
                        }
                    }
                }
            }

            // Return the entry
            return gridEntry
        }
    }

/********** TESTS **********/

    private fun transitionTest()
    {
        val area1 = GenericArea("area_1",
                "Earth",
                R.drawable.earth_test,
                "You leave Earth. ",
                "You land on Earth",
                "The Earth is pretty neat")

        val area2 = GenericArea("area_2",
                "Mars",
                R.drawable.mars_test,
                "You leave Mars. ",
                "You land on Mars.",
                "Mars is radical")

        val area3 = GenericArea("area_3",
                "Sun",
                R.drawable.sun_test,
                "You leave the Sun",
                "You land on the Sun",
                "The sun is so cool")

        area1.addAction(TransitionAction(game,"trans12",Transition(area1,area2)))
        area1.addAction(TransitionAction(game,"trans13",Transition(area1,area3)))
        area2.addAction(TransitionAction(game,"trans21",Transition(area2,area1)))
        area2.addAction(TransitionAction(game,"trans23",Transition(area2,area3)))
        area3.addAction(TransitionAction(game,"trans31",Transition(area3,area1)))
        area3.addAction(TransitionAction(game,"trans32",Transition(area3,area2)))

        val modListTurnEarth = mutableListOf<Mod>()
        modListTurnEarth.add(ImageMod(R.drawable.earth_test_turned)) // When button pressed, change Earth Image
        modListTurnEarth.add(RemoveActionMod("turn_earth")) // Remove itself
        val turnEarthAction = ModifierAction(game, "turn_earth", "Turn the Earth","You turned the Earth!", modListTurnEarth)

        val list2 = mutableListOf<Mod>()
        list2.add(ImageMod(R.drawable.earth_test)) // When button pressed, change Earth Image
        list2.add(RemoveActionMod("turn_earth_back")) // Remove itself
        val turnEarthBackAction = ModifierAction(game,"turn_earth_back","Turn the Earth","You turned the Earth!",list2)

        turnEarthAction.addMod(AddActionMod(turnEarthBackAction)) // Add previous action
        turnEarthBackAction.addMod(AddActionMod(turnEarthAction))

        area1.addAction(turnEarthAction)

        // Simple ModificationAction
        val sayBoopAction = ModifierAction(game,"say_boop","Say Boop")
        sayBoopAction.addMod(TextMod("Boop!"))
        area2.addAction(sayBoopAction)


        val soundAction = ModifierAction(game,"sound_test", "Bell")
        soundAction.addMod(SoundMod(R.raw.bell))
        area3.addAction(soundAction)

        val flagAction = ModifierAction(game,"flag_test","Set Flag!")
        val flagList = mutableListOf<FlagSetter>()
        flagList.add(FlagSetter("swordTaken",true))
        flagAction.addMod(FlagMod(flagList))
        area2.addAction(flagAction)

        // Set starting area and init UI
        game.setCurrentArea(area1)
        initStartArea(game)
    }

}
