gradle wrapper --gradle-version 6.8.3 --distribution-type all --gradle-distribution-sha256-sum 9af5c8e7e2cd1a3b0f694a4ac262b9f38c75262e74a9e8b5101af302a6beadd7
cd .\gradle\wrapper
curl --location --output gradle-wrapper.jar.sha256 https://services.gradle.org/distributions/gradle-6.8.3-wrapper.jar.sha256
echo " gradle-wrapper.jar" >> gradle-wrapper.jar.sha256
sha256sum --check gradle-wrapper.jar.sha256
pause
