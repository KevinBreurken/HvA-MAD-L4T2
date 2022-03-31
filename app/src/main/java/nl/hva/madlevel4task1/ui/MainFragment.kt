package nl.hva.madlevel4task1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.hva.madlevel4task1.GameActionResult
import nl.hva.madlevel4task1.GameActionType
import nl.hva.madlevel4task1.R
import nl.hva.madlevel4task1.databinding.FragmentMainBinding
import nl.hva.madlevel4task1.model.Product
import nl.hva.madlevel4task1.repository.ProductRepository
import java.util.*

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var productRepository: ProductRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productRepository = ProductRepository(requireContext())

        binding.buttonRock.setOnClickListener { onGameActionPressed(GameActionType.ROCK) }
        binding.buttonPaper.setOnClickListener { onGameActionPressed(GameActionType.PAPER) }
        binding.buttonScissor.setOnClickListener { onGameActionPressed(GameActionType.SCISSOR) }
    }

    fun onGameActionPressed(userAction: GameActionType) {
        val computerAction = GameActionType.values()[Random().nextInt(GameActionType.values().size)]
        var gameActionResult = GameActionResult.LOSE;

        if(userAction == computerAction)
            gameActionResult = GameActionResult.DRAW
        else if((userAction == GameActionType.PAPER && computerAction == GameActionType.ROCK) ||
            (userAction == GameActionType.ROCK && computerAction == GameActionType.SCISSOR) ||
            (userAction == GameActionType.SCISSOR && computerAction == GameActionType.PAPER))
            gameActionResult = GameActionResult.WIN

        updateGameUI(userAction,computerAction, gameActionResult);

        mainScope.launch {
            val product = Product(
                gameActionResult.toString(),
                1
            )

            withContext(Dispatchers.IO) {
                productRepository.insertProduct(product)
            }

        }
    }

    fun updateGameUI(userAction: GameActionType,computerAction: GameActionType,gameActionResult: GameActionResult){
        //Update the result text
        binding.resultText.text =
            gameActionResult.toString().lowercase()
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

        updatePlayerActionDisplay(binding.computerImageView,computerAction)
        updatePlayerActionDisplay(binding.playerImageView,userAction)
        //Update the computer graphic
    }

    fun updatePlayerActionDisplay(imageView: ImageView,gameActionType: GameActionType){
        val resourceId: Int = resources.getIdentifier(
            gameActionType.toString().lowercase(), "drawable", requireContext().packageName
        )
        imageView.setImageDrawable(ResourcesCompat.getDrawable(resources,resourceId,null))

    }


}