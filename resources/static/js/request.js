// Fetch data from PHP endpoint for company names and device details
fetch("stuff.php")                .then(response => response.json())
                .then(data => {
                    const companySelect = document.getElementById('company');
                    data.company_names.forEach(company => {
                        const option = document.createElement('option');
                        option.value = company;
                        option.textContent = company;
                        companySelect.appendChild(option);
                    });

                    const itemSelect = document.getElementById('item');
                    data.device_details.forEach(device => {
                        const option = document.createElement('option');
                        option.value = device.device_type;
                        option.textContent = device.device_type;
                        itemSelect.appendChild(option);
                    });

                    document.getElementById('item').addEventListener('change', function() {
                        const selectedItem = this.value;
                        const deviceDetails = data.device_details.find(device => device.device_type === selectedItem);
                        const productSpecifications = document.getElementById('productSpecifications');
                        productSpecifications.value = `Brand: ${deviceDetails.brand}\nModel: ${deviceDetails.model}\nWarranty: ${deviceDetails.warranty}`;
                    });
                })
                .catch(error => console.error('Error fetching data:', error));

            function saveFormDataToLocalStorage(key, data) {
                localStorage.setItem(key, JSON.stringify(data));
            }

            function submitPurchaseRequest(event) {
                event.preventDefault();

                var site = document.getElementById("site").value;
                var item = document.getElementById("item").value;
                var quantity = document.getElementById("quantity").value;
                var productSpecifications = document.getElementById("productSpecifications").value;
                var company = document.getElementById("company").value;
                var requester = document.getElementById("requester").value;
                var technician = document.getElementById("technician").value;

                var formData = {
                    Site: site,
                    Item: item,
                    Quantity: quantity,
                    ProductSpecifications: productSpecifications,
                    company: company,
                    Requester: requester,
                    Technician: technician
                };

                const track = new Track();
                const key = track.pickTwoRandomNumbers();
                saveFormDataToLocalStorage(key, formData);

                window.location.href = "table.html";
            }
