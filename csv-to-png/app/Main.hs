import Graphics.Rendering.Chart.Backend.Cairo
import Graphics.Rendering.Chart.Easy
import System.Environment (getArgs)

main :: IO ()
main = do
    args <- getArgs
    let inputPath = args !! 0
    let fileName = if length args > 1 then args !! 1 else "output.png"
    content <- readFile inputPath
    let dataPoints = map (\s -> let (s1, s2) = break (== ',') s in (read s1 :: Double, read $ drop 1 s2 :: Double)) $ lines content

    toFile def fileName $ do
        setColors [opaque blue]
        plot (line "calc" [dataPoints])
